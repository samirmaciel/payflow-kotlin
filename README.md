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
    
    com.samirmaciel.payflow_kotlin # Root Package
    ├── modules                       
    │   ├── barcodescanner
    |   |   ├── BarcodeScannerActivity
    |   ├── bottomsheetdialog_payment
    |   |   ├── BottomSheetDialogPayment
    |   |   ├── BottomSheetDialogPaymentViewModel
    |   |── bottomsheetdialog_statiment
    |   |   ├── BottomSheetDialogStatiment
    │   ├── home
    |   |   ├── HomeActivity
    |   |   ├── HomeViewModel
    |   ├── login
    |   |   ├── LoginActivity
    |   |── mypayments
    |   |   ├── MyPaymentsSlipsFragment
    |   |   ├── MyPaymentsSlipsViewModel
    │   ├── mystatiments  
    |   |   ├── MyStatimentsFragment
    |   |   ├── MyStatimentsViewModel
    |   ├── register    
    |   |   ├── RegisterActivity
    |   |   ├── RegistrationViewModel
    |   |── splash
    |   |   ├── SplashActivity
    |
    ├── shared               
    │   ├── commom  
    |   |   ├── DateTextWatcher
    |   |   ├── MoneyTextWatcher
    |   |   ├── PaymentsRecyclerViewAdapter
    |   |   ├── StatimentsRecyclerViewAdapter
    |   ├── data 
    |   |   ├── AppDataBase
    |   |   ├── PaymentSlipDataSource
    |   |   ├── StatimentDataSource
    |   |── model 
    |   |   ├── AppDataBase
    |   |   |   ├── PaymentSlipRepository
    |   |   |   ├── RegistrationViewParams
    |   |   |   ├── StatimentRepository
    |   |   ├── PaymentSlipDataSource
    |   |   |   ├── PaymentSlip
    |   |   |   ├── PaymentSlipDAO
    |   |   |   ├── PaymentSlipEntity
    |   |   ├── StatimentDataSource
    |   |   |   ├── Statiment
    |   |   |   ├── StatimentDAO
    |   |   |   ├── StatimentEntity
  


<br />

## Arquitetura 🗼
Para esse app foi utilizado o padrão [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch).

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png?hl=pt-br)
