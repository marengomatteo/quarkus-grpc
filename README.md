# test-grpc

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/test-grpc-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Related Guides

- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin


Per generare i protobuf
```shell script
./gradlew assemble
```

# Kotlin coroutines
Una coroutine è un'istanza di un calcolo sospendibile. È simile a un thread, nel senso che per essere eseguito richiede un blocco di codice che funziona in concomitanza con il resto del codice. Tuttavia, una coroutine non è legata ad alcun thread particolare e può sospendere la sua esecuzione in un thread e riprenderla in un altro.

## Codice coroutines
```kotlin
  launch { 
     // code block
  }
```
launch è un costruttore di coroutin . Lancia una nuova coroutine contemporaneamente al resto del codice, che continua a funzionare in modo indipendente

```kotlin
  runBlocking { ... }
```
runBlocking è anche un costruttore di coroutine che fa da ponte tra il mondo non-coroutine di una funzione main() regolare e il codice con coroutine all'interno delle parentesi graffe. runBlocking significa che il thread che lo esegue viene bloccato per tutta la durata della chiamata, fino a quando tutte le coroutine all'interno di esso completano la loro esecuzione.
N.B. runBlocking viene solitamente utilizzato al livello più alto dell'applicazione e raramente all'interno del codice reale, poiché i thread sono risorse costose e bloccarli è inefficiente e spesso non desiderato.

## CoroutineScope

```kotlin
interface CoroutineScope
```

Definisce uno scope per le nuove coroutine. Ogni costruttore di coroutine (come launch, async, ecc.) è un'estensione di CoroutineScope e eredita il suo coroutineContext per propagare automaticamente tutti i suoi elementi e l'annullamento.

I migliori modi per ottenere un'istanza indipendente dello scope sono le funzioni factory CoroutineScope() e MainScope(), facendo attenzione ad annullare questi scope di coroutine quando non sono più necessari (vedere la sezione sull'uso personalizzato qui sotto per spiegazioni ed esempi).

### CoroutineScope(context)
```kotlin
fun CoroutineScope(context: CoroutineContext): CoroutineScope
```

Crea un CoroutineScope che wrappa il coroutine context.

Se il context non contiene un elemento Job, allora viene creato un Job() predefinito. In questo modo, il fallimento di qualsiasi coroutine figlia in questo scope o l'annullamento dello scope stesso annulla tutti i figli dello scope, proprio come all'interno del blocco coroutineScope.

### coroutineScope(block)
```kotlin
suspend fun <R> coroutineScope(block: suspend CoroutineScope.() -> R): R
```

Crea un CoroutineScope e chiama il blocco di sospensione specificato con questo scope. Lo scope fornito eredita il suo coroutineContext dallo scope esterno, utilizzando il Job di quel contesto come genitore per un nuovo Job.

Questa funzione è progettata per la decomposizione concorrente del lavoro. Quando qualsiasi coroutine figlia in questo scope fallisce, lo scope stesso fallisce, annullando tutte le altre coroutine figlie (per un comportamento diverso, vedere supervisorScope). Questa funzione restituisce non appena il blocco specificato e tutte le sue coroutine figlie sono completate.

## CoroutineDispatcher

```kotlin
abstract class CoroutineDispatcher : AbstractCoroutineContextElement, ContinuationInterceptor
```
Classe base da estendere da tutte le implementazioni del dispatcher di coroutine.

Le seguenti implementazioni standard sono fornite da kotlinx.coroutines come proprietà dell'oggetto Dispatchers:

Dispatchers.Default: è utilizzato da tutti i costruttori standard se non viene specificato alcun dispatcher o qualsiasi altro ContinuationInterceptor nel loro contesto. Utilizza un pool comune di thread di background condivisi. È una scelta appropriata per coroutine intensiva in calcoli che consumano risorse CPU.

Dispatchers.IO:  utilizza un pool condiviso di thread creati su richiesta ed è progettato per il trasferimento di operazioni bloccanti intensive di IO (come l'I/O su file e l'I/O su socket bloccante).

Dispatchers.Main: Un dispatcher di coroutine confinato al thread principale che opera con oggetti UI. Di solito tali dispatcher sono single-threaded.

Dispatchers.Unconfined: avvia l'esecuzione della coroutine nel frame di chiamata corrente fino alla prima sospensione, dopodiché la funzione del costruttore della coroutine restituisce. La coroutine riprenderà in seguito in qualsiasi thread utilizzato dalla funzione di sospensione corrispondente, senza confinarla a un thread o pool specifico. Il dispatcher Unconfined non dovrebbe essere normalmente utilizzato nel codice.

I pool privati di thread possono essere creati con newSingleThreadContext e newFixedThreadPoolContext.


## Job
```kotlin
interface Job : CoroutineContext.Element(source)
```
Un job in background. Concettualmente, un job è una cosa cancellabile con un ciclo di vita che culmina nel suo completamento.

I job possono essere organizzati in gerarchie padre-figlio, dove la cancellazione di un padre porta alla cancellazione immediata di tutti i suoi figli in modo ricorsivo. Il fallimento di un figlio con un'eccezione diversa da CancellationException cancella immediatamente il suo genitore e, di conseguenza, tutti gli altri figli. Questo comportamento può essere personalizzato utilizzando SupervisorJob.

Le istanze più basilari dell'interfaccia Job sono create in questo modo:

Un job di coroutine è creato con il costruttore di coroutine launch. Esegue un blocco di codice specificato e si completa al termine di questo blocco.

CompletableJob è creato con una funzione factory Job(). Viene completato chiamando CompletableJob.complete.

Concettualmente, l'esecuzione di un job non produce un valore di risultato. I job vengono avviati unicamente per i loro effetti collaterali.

## withContext

```kotlin
suspend fun <T> withContext(context: CoroutineContext, block: suspend CoroutineScope.() -> T): T
````

Chiama il blocco di sospensione specificato con un dato contesto di coroutine, si sospende fino al suo completamento e restituisce il risultato.

Il contesto risultante per il blocco è derivato unendo il coroutineContext corrente con il contesto specificato usando coroutineContext + contesto . Questa funzione di sospensione è cancellabile. Controlla immediatamente se vi è stata la cancellazione del contesto risultante e genera un'eccezione di CancellationException se non è attivo.

Le chiamate a withContext il cui argomento del contesto fornisce un CoroutineDispatcher diverso da quello corrente, per necessità, eseguono dispatch aggiuntivi: il blocco non può essere eseguito immediatamente e deve essere disposto per l'esecuzione sul CoroutineDispatcher passato, e quindi quando il blocco completa, l'esecuzione deve tornare al dispatcher originale.

Nota che il risultato dell'invocazione di withContext viene dispacciato nel contesto originale in modo cancellabile con una garanzia di cancellazione pronta, il che significa che se il coroutineContext originale in cui è stato invocato withContext viene cancellato dal momento in cui il suo dispatcher inizia ad eseguire il codice, scarta il risultato di withContext e genera un'eccezione di CancellationException.

Il comportamento di cancellazione descritto sopra è abilitato solo se il dispatcher viene cambiato. Ad esempio, quando si utilizza withContext(NonCancellable) { ... }, non vi è alcun cambiamento di dispatcher e questa chiamata non verrà cancellata né all'ingresso nel blocco all'interno di withContext né all'uscita da esso.