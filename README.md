# Material Lists

An Android XML implementation of Material 3 list items.

## 🙋🏽 Why

Material lists are available in Jetpack Compose but were never implemented in XML. This is a tiny
library that aims to provide most examples of this component specified in
the [design documentation](https://m3.material.io/components/lists/specs).

I have had to re-implement this layout so many times that I have decided to open-source it.

## 🪜 Setup

Include the dependency in your project.

```groovy
implementation "net.nicbell.material-lists:listitem:x.x.x"
```

In order to download the dependency please make sure access to the Maven repository is configured.
You can use JitPack or GitHub.

<details>
  <summary>From JitPack</summary>

JitPack is full configured to work with
the [custom domain](https://jitpack.io/#net.nicbell/material-lists/latest). Just included the Maven
repository and add the dependency as described above.

```gradle
maven { url 'https://jitpack.io' }
```

</details>

<details>
  <summary>From GitHub</summary>

This repo is public but GitHub's Maven Repository needs authentication.

```gradle
maven {
    name = "GitHubPackages"
    url = uri("https://maven.pkg.github.com/nicbell/material-lists")
    credentials {
        username = github_user
        password = github_token
    }
}
```

To download Material Lists you will need to create
a [personal access token](https://github.com/settings/tokens) with `read:packages` scope.

Please do not push your tokens to GitHub, you can store them in `local.properties` instead.

```properties
githubName="username"
githubToken="xxx"
```

</details>

## 🏄🏽 Usage