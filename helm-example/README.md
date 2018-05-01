# Running the fullstack app with Helm

Run all the following commands from the helm-example/ folder

## Create a helm chart for the Terracotta Server

```
helm package terracotta-server/
mv terracotta-server-0.1.0.tgz fullstack/charts/
```

## Import the helm chart for MySQL

```
helm fetch stable/mysql
mv mysql-0.3.7.tgz fullstack/charts/
```

## Run the helm chart for the fullstack app

```
helm install --set mysql.mysqlAllowEmptyPassword=true \
             --set mysql.mysqlRootPassword="" \
             --set mysql.mysqlUser=root \
             --set mysql.mysqlDatabase=demo \
             --set googleApiKey=CHANGEME \
             --set darkskyApiKey=CHANGEME \
             --debug ./fullstack
 ```


