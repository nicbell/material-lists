# Material Lists

![Maven Central](https://img.shields.io/maven-central/v/net.nicbell.material-lists/listitem)
[![JitPack](https://jitpack.io/v/net.nicbell/material-lists.svg)](https://jitpack.io/#net.nicbell/material-lists)
[![codecov](https://codecov.io/gh/nicbell/material-lists/branch/develop/graph/badge.svg?token=YYJ348RZAF)](https://codecov.io/gh/nicbell/material-lists)

An Android XML implementation of Material 3 list items.

<img src="https://user-images.githubusercontent.com/151842/208748928-6e299e55-c38f-44f3-874a-05fe192d67c2.gif" width="340" />

## 🙋🏽 Why

Material lists are available in Jetpack Compose but were never implemented in XML. This is a tiny
library that aims to provide most examples of this component specified in
the [design documentation](https://m3.material.io/components/lists/specs).

I have had to re-implement this layout so many times that I have decided to open-source it.

## 🪜 Setup

Include the dependency in your project.

```groovy
// via GitHub
implementation "net.nicbell.material-lists:listitem:x.x.x"
// via JitPack
implementation 'net.nicbell:material-lists:x.x.x'
```
The name is different as JitPack automatically shortens the name when there is a single artifact.

> To get individual artifacts of multi-module builds use com.github.User.Repo as group Id and
> ModuleName as the artifact Id `com.github.User.Repo:Module:Tag`.
>
> Note: If your project only has a single module then the dependency for that module is
> just `com.github.User:Repo:Tag`.

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

Add the `ListItem` component to your layout.

### Attributes

The following attributes can be changed for a `ListItem`.

| Description                 | Relevant attributes |
|-----------------------------|---------------------|
| Headline text               | `app:head`          |
| Supporting text             | `app:supportText`   |
| Size type - 1, 2 or 3 lines | `app:sizeType`      |

For more info about size types see
the [design documentation](https://m3.material.io/components/lists/specs).

### Content

Leading and trailing content can be added as child views. A content style is require in order to
position the content correctly within the `ListItem`.

| Description              | Style                                  | Required view type                   |
|--------------------------|----------------------------------------|--------------------------------------|
| Leading icon             | `MaterialLists.LeadingIcon`            | `AppCompatImageView`                 |
| Leading image            | `MaterialLists.LeadingImage`           | `AppCompatImageView`                 |
| Leading video thumbnail  | `MaterialLists.LeadingVideoThumbnail`  | `AppCompatImageView`                 |
| Leading avatar image     | `MaterialLists.LeadingAvatar`          | `ShapeableImageView`                 |
| Leading avatar label     | `MaterialLists.LeadingAvatarLabelText` | `MaterialTextView`                   |
| Leading checkbox         | `MaterialLists.LeadingCheckbox`        | `MaterialCheckBox`                   |
| Trailing icon            | `MaterialLists.TrailingIcon`           | `AppCompatImageView`                 |
| Trailing checkbox        | `MaterialLists.TrailingCheckbox`       | `MaterialCheckBox`                   |
| Trailing radio button    | `MaterialLists.TrailingRadioButton`    | `MaterialRadioButton`                |
| Trailing switch          | `MaterialLists.TrailingSwitch`         | `SwitchMaterial` or `MaterialSwitch` |
| Trailing supporting text | `MaterialLists.TrailingSupportingText` | `MaterialTextView`                   |

### Example

```xml

<net.nicbell.materiallists.ListItem 
    android:layout_width="match_parent"
    android:layout_height="wrap_content" 
    app:headline="Headline" 
    app:sizeType="TWO_LINES"
    app:supportText="Support text">

    <androidx.appcompat.widget.AppCompatImageView 
        style="@style/MaterialLists.LeadingIcon"
        android:layout_width="wrap_content" 
        android:layout_height="wrap_content"
        android:src="@drawable/ic_outline_person_24" />

    <com.google.android.material.checkbox.MaterialCheckBox
        style="@style/MaterialLists.TrailingCheckbox" 
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</net.nicbell.materiallists.ListItem>
```

Which will output the following.

<img src="docs/img.png" width="340" />

### Material 2 compatibility

This library was designed to work with `Theme.Material3` themes or themes inheriting from those
themes. The reason for this, is that the library leverages some tokens that are only available in
Material 3. If your activities use `Theme.MaterialComponents` themes you can still use the library
but you will need to apply a `Theme.Material3` theme to the `ListItem` or one of it's parents in your
layout.

### Demo

There is a demo app included with many examples.

<img src="docs/demo.webp" width="340" />
