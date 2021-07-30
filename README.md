![GitHub Cards Preview](https://github.com/samirmaciel/payflow-kotlin/blob/master/bannerpayflow.png)

# Payflow
Organize seus boletos de maneira rápida e prática em um só lugar. 


## UI Design
Design desenvolvido por Tiago Luchtenberg. Link para o figma abaixo 👇👇

[![PAYFLOW](https://img.shields.io/badge/PAYFLOW-FIGMA-orange.svg?style=for-the-badge&logo=figma)](https://www.figma.com/file/kLK7FYnWKMoN68sQXcSniu/PayFlow)


## Desenvolvido com 🛠
- [Kotlin](https://kotlinlang.org/) - Linguagem oficial para desenvolvimento Android.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Para processos assíncrono.
- [Componentes do Jetpack](https://developer.android.com/jetpack?gclid=CjwKCAjwxo6IBhBKEiwAXSYBs_TosuCaJ6xlf6W_tOM8rPcTpvqZbX_3q_PF04woOCkQu3PiRhB39RoCR7sQAvD_BwE&gclsrc=aw.ds) - Conjunto de bibliotecas que ajuda desenvolvedores a seguir as práticas recomendadas, reduzir códigos boilerplate e programar códigos que funcionam de maneira consistente em diferentes dispositivos e versões do Android.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Armazena dados relacionados à IU que não são destruídos nas alterações da IU. 
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - Biblioteca para abstração e mapeamento de objetos com SQlite.

## Estrutura de pacotes 📦
    
    dev.spikeysanju.expenso # Root Package
    ├── di                  # Hilt DI Modules 
    ├── data                # For data handling.
    │   ├── local           # Local Persistence Database. Room (SQLite) database
    |   │   ├── dao         # Data Access Object for Room   
    |   |   |── database    # Database Instance
    |
    ├── model               # Model classes [Transaction]
    |
    |-- repo                # Used to handle all data operations
    |
    ├── view                # Activity/Fragment View layer
    │   ├── main            # Main root folder
    |   │   ├── main        # Main Activity for RecyclerView
    |   │   └── viewmodel   # Transaction ViewModel
    |   │   ├── adapter     # Adapter for RecyclerView
    │   ├── Dashboard       # Dashboard root folder
    |   |   |__ dashboard   # Dashboard 
    │   ├── Add             # Add Transaction root folder
    |   |   |__ add         # Add Transaction 
    │   ├── Edit            # Edit Transaction root folder
    |   |   |__ edit        # Edit Transaction
    │   ├── Details         # Add Transaction root folder
    |   |   |__ details     # Transaction Details
    │   ├── About           # About root folder
    |   |   |__ about       # About 
    │   ├── Dialog          # All Dialogs root folder
    |   |   |__ dialog      # Error Dialog 
    ├── utils               # All extension functions


<br />
