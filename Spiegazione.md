# Quarkus
Quarkus consente agli sviluppatori Java di creare applicazioni per un mondo moderno e nativo del cloud. L'obiettivo è quello di rendere Java la piattaforma principale in Kubernetes e ambienti serverless, offrendo agli sviluppatori un framework per affrontare una gamma più ampia di architetture di applicazioni distribuite.

## Spring boot vs quarkus
https://www.baeldung.com/spring-boot-vs-quarkus

# gRPC

gRPC è un moderno framework RPC (Remote Procedure Call) open source ad alte prestazioni che può funzionare in qualsiasi ambiente. È in grado di connettere efficientemente i servizi all'interno e tra i data center con supporto plug-in per il bilanciamento del carico, il tracciamento, il controllo della salute e l'autenticazione. È inoltre applicabile per connettere dispositivi, applicazioni mobili e browser ai servizi di backend.

In gRPC, un'applicazione client può chiamare direttamente un metodo su un'applicazione server su una macchina diversa come se fosse un oggetto locale, rendendo più facile la creazione di applicazioni e servizi distribuiti. Come in molti sistemi RPC, gRPC si basa sull'idea di definire un servizio, specificando i metodi che possono essere chiamati in remoto con i loro parametri e tipi di ritorno. Sul lato server, il server implementa questa interfaccia ed esegue un server gRPC per gestire le chiamate dei client. Sul lato client, il client ha uno stub che fornisce gli stessi metodi del server.

## protocol buffer

I Protocol Buffers sono un meccanismo estensibile neutro rispetto al linguaggio e alla piattaforma per serializzare dati strutturati. È simile a JSON, però è più piccolo e veloce e genera binding nativi per il linguaggio. Si definisce una volta come si desidera strutturare i dati e quindi si può utilizzare del codice sorgente speciale generato per scrivere e leggere facilmente i dati strutturati da e verso una varietà di flussi di dati e utilizzando una varietà di linguaggi.

I protocol buffers sono una combinazione del linguaggio di definizione (creato nei file .proto), del codice che il compilatore proto genera per interfacciarsi con i dati, delle librerie di runtime specifiche del linguaggio, del formato di serializzazione per i dati scritti su un file (o inviati attraverso una connessione di rete), e dei dati serializzati.

I protocol buffers forniscono un formato di serializzazione per pacchetti di dati strutturati e tipizzati che possono essere fino a pochi megabyte di dimensione. Il formato è adatto sia per il traffico di rete effimero che per l'archiviazione dei dati a lungo termine. I protocol buffers possono essere estesi con nuove informazioni senza invalidare i dati esistenti o richiedere che il codice venga aggiornato.

### Benefit di proto

I protocol buffers sono ideali per qualsiasi situazione in cui è necessario serializzare dati strutturati, simili a record, tipizzati in modo neutro rispetto al linguaggio e alla piattaforma, in modo estensibile. Sono più comunemente utilizzati per definire protocolli di comunicazione (insieme a gRPC) e per l'archiviazione dei dati.

Alcuni dei vantaggi nell'uso dei protocol buffers includono:

1. **Archiviazione compatta dei dati**: I dati vengono memorizzati in modo efficiente, occupando meno spazio rispetto ad altri formati di serializzazione come JSON.

2. **Parsing veloce**: I protocol buffers consentono di analizzare rapidamente i dati, fornendo prestazioni ottimizzate.

3. **Disponibilità in molti linguaggi di programmazione**: Sono supportati da una vasta gamma di linguaggi di programmazione, consentendo l'interoperabilità tra diverse piattaforme.

4. **Funzionalità ottimizzate tramite classi generate automaticamente**: Le classi necessarie per manipolare i dati vengono generate automaticamente dal compilatore dei protocol buffers, offrendo funzionalità ottimizzate e una maggiore facilità d'uso.

### quando non ha senso utilizzare protobuf
I protocol buffers non sono adatti a tutti i tipi di dati. In particolare:

1. **Dimensione dei dati**: I protocol buffers tendono a assumere che interi messaggi possano essere caricati in memoria contemporaneamente e non siano più grandi di un grafo degli oggetti. Per dati che superano alcuni megabyte, si dovrebbe considerare una soluzione diversa; quando si lavora con dati più grandi, si possono ottenere effettivamente diverse copie dei dati a causa delle copie serializzate, il che può causare picchi inaspettati nell'uso della memoria.

2. **Serializzazione**: Quando i protocol buffers vengono serializzati, gli stessi dati possono avere molte diverse serializzazioni binarie. Non è possibile confrontare due messaggi per uguaglianza senza analizzarli completamente.

3. **Compressione**: I messaggi protocol buffer non sono compressi. Anche se i messaggi possono essere compressi con zip o gzip come qualsiasi altro file, algoritmi di compressione specializzati come quelli utilizzati da JPEG e PNG produrranno file molto più piccoli per dati del tipo appropriato.

4. **Efficienza**: I messaggi di protocol buffer sono meno efficienti sia in termini di dimensione che di velocità per molti usi scientifici ed ingegneristici che coinvolgono grandi array multidimensionali di numeri in virgola mobile. Per queste applicazioni, formati come FITS e simili hanno meno overhead.

5. **Supporto in linguaggi non orientati agli oggetti**: I messaggi di protocol buffer non sono ben supportati in linguaggi non orientati agli oggetti popolari nel calcolo scientifico, come Fortran e IDL.

6. **Auto-descrizione dei dati**: I messaggi di protocol buffer non descrivono intrinsecamente i propri dati, ma dispongono di uno schema completamente riflessivo che può essere utilizzato per implementare l'auto-descrizione. Cioè, non è possibile interpretare completamente un messaggio senza accesso al relativo file .proto.

7. **Standardizzazione**: I protocol buffers non sono uno standard formale di nessuna organizzazione. Ciò li rende inadatti per l'uso in ambienti con requisiti legali o altri requisiti per basarsi su standard.


