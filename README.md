Tecnologias utilizadas

1- Spring
2- Postgres
3- Hikari

Para ejecutar la aplicacion en la PC.
Descargar `docker`
Descargar la ultima version de la rama `master` en su PC
Dentro del proyecto `cd ./dynamic-report-back`

Primero creamos el jar dentro de /build/lib/*
```
gradle bootJar
```

Ejecute el siguiente comando para construir el contenedor:
```
docker build --build-arg JAR_FILE="build/libs/*.jar" -t mhschimpf/dynamic-report-back .
```

Enviamos al hub de docker
```
 docker push mhschimpf/dynamic-report-back
```

Ejecute el siguiente comando para ejecutar el contenedor
```
docker run -p 8080:8080 -d mhschimpf/dynamic-report-back
```

Con el siguiente comamndo veremos si el contenedor esta arriba
```
docker ps
```

y se tiene que ver algo como esto
``` 
docker ps
CONTAINER ID   IMAGE                               COMMAND                  CREATED       STATUS       PORTS                                       NAMES
10a2db9d749d   mhschimpf/dynamic-report-back   "java -jar /producci…"   n hours ago   Up 6 hours   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp   silly_feistel
```

Para ver el log del contenedor ejecute el comando
```
docker logs 10a2db9d749d
```

`10a2db9d749d` es el `CONTAINER ID` donde se puede obtener ejecutando `docker ps` o sino `docker ps -a` si no se levanto correctamente


Para hacer login al hub de docker
```
docker login -u mhschimpf
Pass: Soporte1553
```


Ahora puede enviar la imagen de la aplicación a Docker Hub usando la etiqueta que creó anteriormente `hschimpf/produccion_regopar_back`
esto hace que se almacene en un repositorio la ultima version de la imagen

```
docker push mhschimpf/dynamic-report-back
```

Cuando se quiera obtener la imagen enviada a se ejecuta el siguiente comando

```
docker pull mhschimpf/dynamic-report-back
```
