buildscript {
    // ...
    repositories {
        // ...
        maven {
            url 'https://repo.cuba-platform.com/content/groups/premium'
            credentials {
                username(rootProject.hasProperty('premiumRepoUser') ?
                        rootProject['premiumRepoUser'] : System.getenv('CUBA_PREMIUM_USER'))
                password(rootProject.hasProperty('premiumRepoPass') ?
                        rootProject['premiumRepoPass'] : System.getenv('CUBA_PREMIUM_PASSWORD'))
            }
        }
    }