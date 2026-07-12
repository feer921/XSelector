# Maven Central 发布流程

本项目使用 `gradle/maven-central-publish.gradle` 作为通用发布脚本，`TheXSelector/maven_publish.gradle` 只是兼容入口。

## 前置条件

- Central Portal 中已验证 `io.github.feer921` namespace。
- Central Portal 已生成 User Token。
- 准备 GPG/PGP 私钥和签名密码。
- 不要把真实 token、私钥、密码提交到仓库。

## Secret 提供方式

脚本按以下顺序读取配置：

1. Gradle properties，例如 `-PmavenCentralUsername=...`
2. 环境变量，例如 `MAVEN_CENTRAL_USERNAME`
3. 外部 secret 文件，例如 `./maven-secret.properties`
4. 非敏感配置文件 `maven.properties`

默认 secret 文件路径是根目录 `maven-secret.properties`，该文件已在 `.gitignore` 中忽略。也可以通过下面方式指定外部路径：

```bash
bash gradlew :TheXSelector:publishReleaseToMavenCentralPortal \
  -PmavenCentralSecretFile=/absolute/path/to/maven-secret.properties
```

可参考 `maven-secret.properties.example`。

## 推荐环境变量

```bash
export MAVEN_CENTRAL_USERNAME='token-username'
export MAVEN_CENTRAL_PASSWORD='token-password'
export MAVEN_CENTRAL_SIGNING_KEY="$(gpg --armor --export-secret-keys <KEY_ID>)"
export MAVEN_CENTRAL_SIGNING_KEY_ID='<KEY_ID>'
export MAVEN_CENTRAL_SIGNING_PASSWORD='gpg-passphrase'
```

也可以改用 `MAVEN_CENTRAL_SIGNING_KEY_FILE=/absolute/path/to/private-key.asc` 指向外部私钥文件。

## 本地验证

```bash
bash gradlew :TheXSelector:publishReleasePublicationToMavenLocal
```

该命令不需要 Central Portal token。无签名信息时，签名任务会跳过。

## 发布到 Central Portal

```bash
bash gradlew :TheXSelector:publishReleaseToMavenCentralPortal
```

这个任务会：

1. 生成 release AAR、sources jar、placeholder javadoc jar、POM。
2. 使用外部提供的 PGP/GPG 私钥签名。
3. 发布到 Central Portal OSSRH Staging API。
4. 调用 `/manual/upload/defaultRepository/<namespace>` 把部署导入 Central Portal。

`MAVEN_CENTRAL_PUBLISHING_TYPE` 默认为 `user_managed`，发布后需要在 Central Portal 页面确认并 release。若希望通过接口尝试自动 release，可以改为：

```properties
MAVEN_CENTRAL_PUBLISHING_TYPE=automatic
```

## 关键任务

- `:TheXSelector:publishReleasePublicationToMavenLocal`
- `:TheXSelector:publishReleasePublicationToMavenCentralRepository`
- `:TheXSelector:uploadMavenCentralDeployment`
- `:TheXSelector:publishReleaseToMavenCentralPortal`
- `:TheXSelector:validateMavenCentralPublishConfig`
