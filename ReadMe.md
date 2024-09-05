# **Reown - Kotlin**

The communications protocol for web3, Reown brings the ecosystem together by enabling hundreds of wallets and apps to securely connect and interact. This repository contains Kotlin implementation of
Reown protocols for Android applications.

####

## BOM Instructions:

To help manage compatible dependencies stay in sync, we've introduced a [BOM](https://docs.gradle.org/current/userguide/platforms.html#sub:bom_import) to the Kotlin SDK. With this, you only need to
update the BOM version to get the latest SDKs. Just add the BOM as a dependency and then list the SDKs you want to include into your project.

### example build.gradle.kts

```kotlin
dependencies {
    implementation(platform("com.reown:android-bom:{BOM version}"))
    implementation("com.reown:android-core")
    implementation("com.reown:walletkit")
}
```

## SDK Chart

| BOM                                                                                     | [Core SDK](core/android) | [Sign SDK](protocol/sign) | [WalletKit](product/web3wallet) | [AppKit](product/web3modal) |
|-----------------------------------------------------------------------------------------|--------------------------|---------------------------|---------------------------------|-----------------------------|
| 1.35.1                                                                                  | 1.35.1                   | 2.35.1                    | 1.35.1                          | 1.6.5                       |
| 1.35.0                                                                                  | 1.35.0                   | 2.35.0                    | 1.35.0                          | 1.6.4                       |
| 1.34.1                                                                                  | 1.34.1                   | 2.34.1                    | 1.34.1                          | 1.6.3                       |
| 1.34.0                                                                                  | 1.34.0                   | 2.34.0                    | 1.34.0                          | 1.6.2                       |
| 1.33.1                                                                                  | 1.33.1                   | 2.33.1                    | 1.33.1                          | 1.6.1                       |
| 1.33.0                                                                                  | 1.33.0                   | 2.33.0                    | 1.33.0                          | 1.6.0                       |
| 1.32.1                                                                                  | 1.32.0                   | 2.32.0                    | 1.32.1                          | 1.5.4                       |
| 1.31.3                                                                                  | 1.31.2                   | 2.31.2                    | 1.31.2                          | 1.5.2                       |
| 1.31.1                                                                                  | 1.31.1                   | 2.31.1                    | 1.31.1                          | 1.5.1                       |
| 1.31.0<sup>[**](https://gist.github.com/TalhaAli00/7b9e1cadf19b9dc5141cd033aa4e6172)    | 1.31.0                   | 2.31.0                    | 1.31.0                          | 1.5.0                       |
| 1.30.0                                                                                  | 1.30.0                   | 2.30.0                    | 1.30.0                          | 1.4.0                       |
| 1.23.0                                                                                  | 1.28.0                   | 2.26.0                    | 1.21.0                          | 1.3.0                       |
| 1.22.2                                                                                  | 1.27.2                   | 2.25.2                    | 1.20.2                          | 1.2.2                       |
| 1.22.1                                                                                  | 1.27.0                   | 2.25.0                    | 1.20.0                          | 1.2.1                       |
| 1.22.0                                                                                  | 1.27.0                   | 2.25.0                    | 1.20.0                          | 1.2.0                       |
| 1.21.1                                                                                  | 1.26.0                   | 2.24.0                    | 1.19.0                          | 1.1.1                       |
| 1.21.0                                                                                  | 1.26.0                   | 2.24.0                    | 1.19.0                          | 1.1.0                       |
| 1.20.0                                                                                  | 1.25.0                   | 2.23.0                    | 1.18.0                          | 1.0.0                       |
| 1.19.1                                                                                  | 1.24.0                   | 2.22.0                    | 1.17.0                          | 1.0.0-beta02                |
| 1.19.0                                                                                  | 1.24.0                   | 2.22.0                    | 1.17.0                          | 1.0.0-beta01                |
| 1.18.0                                                                                  | 1.23.0                   | 2.21.0                    | 1.16.0                          | 1.0.0-alpha11               |
| 1.17.2                                                                                  | 1.22.1                   | 2.20.1                    | 1.15.1                          | 1.0.0-alpha10               |
| 1.17.1                                                                                  | 1.22.0                   | 2.20.0                    | 1.15.0                          | 1.0.0-alpha09               |
| 1.17.0                                                                                  | 1.22.0                   | 2.20.0                    | 1.15.0                          | 1.0.0-alpha09               |
| 1.16.0                                                                                  | 1.21.0                   | 2.19.0                    | 1.14.0                          |                             |
| 1.15.0                                                                                  | 1.20.0                   | 2.18.0                    | 1.13.0                          |                             |
| 1.14.0                                                                                  | 1.19.0                   | 2.17.0                    | 1.12.0                          |                             |
| 1.13.1                                                                                  | 1.18.0                   | 2.16.0                    | 1.11.0                          |                             |
| 1.12.0                                                                                  | 1.17.0                   | 2.15.0                    | 1.10.0                          |                             |
| 1.11.1                                                                                  | 1.16.1                   | 2.14.1                    | 1.9.1                           |                             |
| 1.11.0                                                                                  | 1.16.0                   | 2.14.0                    | 1.9.0                           |                             |
| 1.10.0                                                                                  | 1.15.0                   | 2.13.0                    | 1.8.0                           |                             |
| 1.9.3                                                                                   | 1.14.3                   | 2.12.3                    | 1.7.3                           |                             |
| 1.9.2                                                                                   | 1.14.2                   | 2.12.2                    | 1.7.2                           |                             |
| 1.9.1                                                                                   | 1.14.1                   | 2.12.1                    | 1.7.1                           |                             |
| 1.9.0<sup>[**](https://github.com/WalletConnect/WalletConnectKotlinV2/issues/821)</sup> | 1.14.0                   | 2.12.0                    | 1.7.0                           |                             |
| 1.8.0                                                                                   | 1.13.0                   | 2.11.0                    | 1.6.0                           |                             |
| 1.7.0                                                                                   | 1.12.0                   | 2.10.0                    | 1.5.0                           |                             |
| 1.6.1                                                                                   | 1.11.1                   | 2.9.1                     | 1.4.1                           |                             |
| 1.6.0                                                                                   | 1.11.0                   | 2.9.0                     | 1.4.0                           |                             |
| 1.5.0                                                                                   | 1.10.0                   | 2.8.0                     | 1.3.0                           |                             |
| 1.4.1                                                                                   | 1.9.1                    | 2.7.1                     | 1.2.1                           |                             |
| 1.3.0                                                                                   | 1.8.0                    | 2.6.0                     | 1.1.0                           |                             |
| 1.2.0                                                                                   | 1.7.0                    | 2.5.0                     | 1.0.0                           |                             |
| 1.1.1                                                                                   | 1.6.0                    | 2.4.0                     |                                 |                             |
| 1.0.1                                                                                   | 1.5.0                    | 2.3.1                     |                                 |                             |
|                                                                                         | 1.4.0                    | 2.2.0                     |                                 |                             |
|                                                                                         | 1.3.0                    | 2.1.0                     |                                 |                             |
|                                                                                         | 1.2.0                    |                           |                                 |                             |
|                                                                                         | 1.1.0                    | 2.0.0                     |                                 |                             |
|                                                                                         | 1.0.0                    | 2.0.0-rc.5                |                                 |                             |

## License

Reown is released under the Apache 2.0 license. [See LICENSE](/LICENSE) for details.
