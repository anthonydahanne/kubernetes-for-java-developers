# Kubernetes for Java Developers

## [Memory Eater](./memoryeater/)

Example to demonstrate the importance of 2 recent JVM options to give the JVM awareness of cgroups limitation in the current container :

```-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap```

## [Fullstack app deployment on kubernetes](https://github.com/ehcache/ehcache3-samples/tree/master/fullstack/src/main/kubernetes)

Spring boot application, that depends on a Terracotta Server and a MySQL database

## [Helm example](./helm-example/)

Sample Helm examples to create Helm charts for the open source Terracotta server and the FullStack app

## [Kubernetes client](./kubernetes-client)

A simple webapp, to showcase [the Java Kubernetes API](https://github.com/kubernetes-client/java)