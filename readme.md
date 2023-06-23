# GraalVM native image compilation is failing in service when I do `./gradlew nativeCompile`

I upgraded to 3.1.1 and the `service` module no longer compiles when using gradle and java 19 graalvm. interestingly, the `client` still seems to work.

I'm using:

```shell
openjdk 19.0.1 2022-10-18
OpenJDK Runtime Environment GraalVM CE 22.3.0 (build 19.0.1+10-jvmci-22.3-b08)
OpenJDK 64-Bit Server VM GraalVM CE 22.3.0 (build 19.0.1+10-jvmci-22.3-b08, mixed mode, sharing)
```

the error im getting is:

```
> Task :compileJava
> Task :processResources
> Task :classes
> Task :resolveMainClassName

> Task :processAot

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.1)

2023-06-23T11:42:55.499+10:00  INFO 14501 --- [           main] com.example.service.ServiceApplication   : Starting ServiceApplication using Java 19.0.1 with PID 14501 (/Users/jlong/Downloads/311/service/build/classes/java/main started by jlong in /Users/jlong/Downloads/311/service)
2023-06-23T11:42:55.501+10:00  INFO 14501 --- [           main] com.example.service.ServiceApplication   : No active profile set, falling back to 1 default profile: "default"
2023-06-23T11:42:55.850+10:00  INFO 14501 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JDBC repositories in DEFAULT mode.
2023-06-23T11:42:55.870+10:00  INFO 14501 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 17 ms. Found 1 JDBC repository interfaces.

> Task :compileAotJava

> Task :processAotResources
> Task :aotClasses
> Task :jar

> Task :generateResourcesConfigFile
[native-image-plugin] Resources configuration written into /Users/jlong/Downloads/311/service/build/native/generated/generateResourcesConfigFile/resource-config.json

> Task :nativeCompile
[native-image-plugin] GraalVM Toolchain detection is disabled
[native-image-plugin] GraalVM location read from environment variable: JAVA_HOME
[native-image-plugin] Native Image executable path: /Users/jlong/.sdkman/candidates/java/22.3.r19-grl/lib/svm/bin/native-image
[2m========================================================================================================================[0m
[1;34m]8;;https://www.graalvm.org/native-image/\GraalVM Native Image]8;;\[0m: Generating '[1mservice[0m' (]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-imagekind\executable]8;;\)...
[2m========================================================================================================================[0m
[0;34m[1/7] [0m[1;34m]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#stage-initializing\Initializing]8;;\...[0m                                                                                    [2m(6.6s @ 0.29GB)[0m
 ]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-version-info\Version info]8;;\: 'GraalVM 22.3.0 Java 19 CE'
 ]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-java-version-info\Java version info]8;;\: '19.0.1+10-jvmci-22.3-b08'
 ]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-ccompiler\C compiler]8;;\: cc (apple, arm64, 14.0.3)
 ]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-gc\Garbage collector]8;;\: Serial GC
 1 ]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-user-specific-features\user-specific feature(s)]8;;\
 - org.springframework.aot.nativex.feature.PreComputeFieldFeature
Field org.apache.commons.logging.LogAdapter#log4jSpiPresent set to true at build time
Field org.apache.commons.logging.LogAdapter#log4jSlf4jProviderPresent set to true at build time
Field org.apache.commons.logging.LogAdapter#slf4jSpiPresent set to true at build time
Field org.apache.commons.logging.LogAdapter#slf4jApiPresent set to true at build time
Field org.springframework.aot.AotDetector#inNativeImage set to true at build time
Field org.springframework.core.NativeDetector#inNativeImage set to true at build time
Field org.springframework.format.support.DefaultFormattingConversionService#jsr354Present set to false at build time
Field org.springframework.core.KotlinDetector#kotlinPresent set to false at build time
Field org.springframework.core.KotlinDetector#kotlinReflectPresent set to false at build time
Field org.springframework.cglib.core.AbstractClassGenerator#inNativeImage set to true at build time
Field org.springframework.boot.logging.logback.LogbackLoggingSystem$Factory#PRESENT set to true at build time
Field org.springframework.boot.logging.java.JavaLoggingSystem$Factory#PRESENT set to true at build time
Field org.springframework.web.servlet.view.InternalResourceViewResolver#jstlPresent set to false at build time
Field org.springframework.boot.logging.log4j2.Log4J2LoggingSystem$Factory#PRESENT set to false at build time
Field org.springframework.transaction.interceptor.TransactionAspectSupport#vavrPresent set to false at build time
Field org.springframework.transaction.interceptor.TransactionAspectSupport#reactiveStreamsPresent set to false at build time
Field org.springframework.http.converter.json.Jackson2ObjectMapperBuilder#jackson2XmlPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#romePresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jaxb2Present set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jackson2Present set to true at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jackson2XmlPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jackson2SmilePresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jackson2CborPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#gsonPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#jsonbPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#kotlinSerializationCborPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#kotlinSerializationJsonPresent set to false at build time
Field org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#kotlinSerializationProtobufPresent set to false at build time
Field org.springframework.web.client.RestTemplate#romePresent set to false at build time
Field org.springframework.web.client.RestTemplate#jaxb2Present set to false at build time
Field org.springframework.web.client.RestTemplate#jackson2Present set to true at build time
Field org.springframework.web.client.RestTemplate#jackson2XmlPresent set to false at build time
Field org.springframework.web.client.RestTemplate#jackson2SmilePresent set to false at build time
Field org.springframework.web.client.RestTemplate#jackson2CborPresent set to false at build time
Field org.springframework.web.client.RestTemplate#gsonPresent set to false at build time
Field org.springframework.web.client.RestTemplate#jsonbPresent set to false at build time
Field org.springframework.web.client.RestTemplate#kotlinSerializationCborPresent set to false at build time
Field org.springframework.web.client.RestTemplate#kotlinSerializationJsonPresent set to false at build time
Field org.springframework.web.client.RestTemplate#kotlinSerializationProtobufPresent set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#reactorPresent set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#rxjava3Present set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#kotlinCoroutinesPresent set to false at build time
Field org.springframework.core.ReactiveAdapterRegistry#mutinyPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jaxb2Present set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jackson2Present set to true at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jackson2XmlPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jackson2SmilePresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#gsonPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#jsonbPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#kotlinSerializationCborPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#kotlinSerializationJsonPresent set to false at build time
Field org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter#kotlinSerializationProtobufPresent set to false at build time
[0;34m[2/7] [0m[1;34m]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#stage-analysis\Performing analysis]8;;\...[0m  [2m[*][0m                                                                       [2m(10.0s @ 1.52GB)[0m
   8,169 (78.42%) of 10,417 classes ]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-reachability\reachable]8;;\
   8,860 (53.00%) of 16,716 fields ]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-reachability\reachable]8;;\
Field org.springframework.data.convert.JMoleculesConverters#JMOLECULES_PRESENT set to false at build time
  29,537 (40.06%) of 73,741 methods ]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-reachability\reachable]8;;\
     985 classes,   358 fields, and 5,936 methods ]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-reflection-registrations\registered for reflection]8;;\
       1 native library: -framework CoreServices

[2m------------------------------------------------------------------------------------------------------------------------[0m
                        0.6s (3.6% of total time) in 19 ]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-garbage-collections\GCs]8;;\ | ]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-peak-rss\Peak RSS]8;;\: 3.58GB | ]8;;https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md#glossary-cpu-load\CPU load]8;;\: 4.65
[2m========================================================================================================================[0m
Failed generating '[1mservice[0m' after 16.8s.
[0m
> Task :nativeCompile FAILED
9 actionable tasks: 9 executed


Warning: Method com.zaxxer.hikari.HikariConfig.setInitializationFailFast(boolean) not found.
Warning: Method com.zaxxer.hikari.HikariConfig.setJdbc4ConnectionTest(boolean) not found.
Warning: Method com.zaxxer.hikari.HikariConfig.setScheduledExecutorService(ScheduledThreadPoolExecutor) not found.
Error: Virtual threads are used in code, but are not currently available or active. Use JDK 19 with preview features enabled (--enable-preview).
Error: Use -H:+ReportExceptionStackTraces to print stacktrace of underlying exception
Error: Image build request failed with exit status 1

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':nativeCompile'.
> Process 'command '/Users/jlong/.sdkman/candidates/java/current/bin/native-image'' finished with non-zero exit value 1

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 20s

```


