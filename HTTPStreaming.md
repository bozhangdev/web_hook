HTTP streaming est une approche couramment utilisée pour les flux haut débit, qui consiste à ne pas fermer la connexion TCP à la n d’un cycle requête-réponse HTTP et à transmettre régulièrement les nouveaux éléments au fur et à mesure de leur production grâce au mode de transfert par bloc du protocole HTTP. Il implique de conserver un ensemble d’informations en mémoire, le coût augmentant donc avec le nombre de flux servis en parallèle. De fait, HTTP streaming est plus adapté au flux transportant beaucoup d’éléments par seconde, tandis qu’au contraire les Web hooks sont à privilégier lorsque les éléments sont très espacés dans le temps.



On peut voir que le but de notre serveur est que transporter les nouveaux éléments aux clients, il ne faut pas  transporter beaucoup d’éléments par seconde, donc il sont assez espacés dans le temps, en ce cas là, il il n'est pas nécessaire de utiliser HTTP Streaming, Web Hook est plus adapté a ce cas. 



On peut utiliser HTTP Streaming quand même, le serveur fournit une interface qui s'appelle "remote", il contient le fonction pour vérifier s'il y a des nouveaux documents, s'il y en a, le serveur va créer un objet de nouveau document qui peut être sérialisé et transporter à client, s'il n'y en a pas, il retour rien. Pour un client, chaque fois il  veut obtenir un nouveau document, il faut demander au serveur, le client peut envoyer le demande régulièrement, avec cette façon, le client ne peut pas obtenir le document quand le document est  déposé sur serveur, c'est pas assez pratique, mais avec web hook, quand les nouveaux documents sont détecté par serveur il va appel les callback fonctions, donc le client peut obtenir le document dés que le document est crée.

Sur côté serveur, il fournir un class qui stocker les information d'un document.

```java
public class Document implements Serializable{
  ...
}
```

un interface remote.

```java
public interface FaconHTTPStreaming extends Remote{
    Document getDocument(String documentname) throws RemoteException;
}
```

un class pour l'implementer.

```java
public class FaconHTTPStreamingImpl extends UnicastRemoteObject implements FaconHTTPStreaming{
    public Document getDocument(String documentname) throws RemoteException{
    	...
    };
}
```

le serveur binding les objet de type FaconHTTPStreaming.

```java
FaconHTTPStreaming sender = new FaconHTTPStreamingImpl();
LocateRegistry.createRegistry(32479);
Naming.bind("rmi://127.0.0.1:32479/sender", sender);
```



après client peut invoquer ce méthod pour obtenir le document.

```java
FaconHTTPStreaming sender = (FaconHTTPStreaming)Naming.lookup("rmi://127.0.0.1:32479/sender");
Document document = sender.getDocument(String documentname);
```

