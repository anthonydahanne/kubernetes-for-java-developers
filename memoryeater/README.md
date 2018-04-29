# Testing JVM Memory limits in a container, using Kubernetes

We first build the image :

    docker build -t memoryeater .


1. we simply run it on Kubernetes :

       kubectl run test -it --image=memoryeater  --restart=Never

   We notice that the maximum of memory was set to 1324 MB by the JVM; roughly 1/4th of the available memory on the machine

2. we run on Kubernetes with a memory limit :

       kubectl run test500 -it --image=memoryeater  --restart=Never --limits='memory=500Mi'

   The JVM crashed with the Out of Memory killer, because it did not pick up the memory constraint given to the container :

       $ kubectl get pod --show-all
       NAME      READY     STATUS      RESTARTS   AGE
       test      0/1       Completed   0          4m
       test500   0/1       OOMKilled   0          35s

3. we run on Kubernetes with a memory limit and the JVM options :

       $ kubectl run test500cgroups -it --image=memoryeater   --env JAVA_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap"  --restart=Never --limits='memory=500Mi'

   The JVM no longer crashes, it did not allocate more than 1/4th of the 500MB memory limit

       $ kubectl get pod --show-all
       NAME             READY     STATUS      RESTARTS   AGE
       test             0/1       Completed   0          9m
       test500          0/1       OOMKilled   0          6m
       test500cgroups   0/1       Completed   0          42s
