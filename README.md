# rick-and-morty
This is a sample project to try out how **[Ingress](https://kubernetes.io/docs/concepts/services-networking/ingress/)** works.

As a first step, make sure [`minikube`](https://github.com/kubernetes/minikube) installed on your machine.

To Play aroung with Ingress, 

* Switch to project directory 
* Start your local K8S cluster ==> `minikube start`
* Enable Ingress on your local cluster ==> `minikube addons enable ingress`
* Create a deployment for **rick** project ==> `kubectl apply -f rick_deployment.yml` 
* Create a deployment for **morty** project ==> `kubectl apply -f morty_deployment.yml`
* Create a service for **rick** deployment ==> `kubectl apply -f rick_service.yml`
* Create a service for **morty** deployment ==> `kubectl apply -f morty_service.yml`
* Create Ingress rules ==> `kubectl apply -f ingress.yml`
* Update **/etc/hosts** file to ==> `echo "$(minikube ip) rick-and-morty.com" | sudo tee -a /etc/hosts`

---

Docker Images for rick and morty projects:
- https://cloud.docker.com/repository/docker/seckinozden/rick
- https://cloud.docker.com/repository/docker/seckinozden/morty

If you have problems on pulling docker images, create a secret on your local env with the following command. 
`kubectl create secret docker-registry regcred --docker-server=https://cloud.docker.com --docker-username=[YOUR_DOCKERHUB_USERNAME] --docker-password=[YOUR_DOCKERHUB_PWD] --docker-email=[YOUR_DOCKERHUB_EMAIL]`
 
This secret is used on the deployment definitions on the parts which is marked with star (*).
```
    spec:
      containers:
      - name: morty
        image: seckinozden/morty:0.3
        ports:
        - containerPort: 8080
   ** imagePullSecrets: **
   ** - name: regcred **
```

---

On the Ingress configuration, paths started with `/rick` configured to redirected to rick-service and paths started with `/morty` configured to redirected to `morty-service`
```
  - host: rick-and-morty.com
    http:
      paths:
      - path: /rick/?(.*)
        backend:
          serviceName: rick-service
          servicePort: 80
      - path: /morty/?(.*)
        backend:
          serviceName: morty-service
          servicePort: 80
```


 - http://rick-and-morty.com/rick/.       ======> redirected to rick-service
 - http://rick-and-morty.com/rick/hello.  ======> redirected to rick-service

 - http://rick-and-morty.com/morty/       ======> redirected to morty-service
 - http://rick-and-morty.com/morty/hello. ======> redirected to morty-service
