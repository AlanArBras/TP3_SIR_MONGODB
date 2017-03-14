# [TP3: MONGODB + Morphia / Redis](https://docs.google.com/document/d/17UJm-iS05D5CxLWjhmfujAwQTbC4rG1tjG3F-X7J1P8/edit?usp=sharing])
### Rappels:
* Les "tables" d'une base de données relationnelle (BDR) correspondent à des "collections" en BDOD.
* Une collection peut accueillir plusieurs structures de données différentes (une entrée = un "document").
    
### Quelles sont les limites d’une base données orientées document (BDOD) ?
Avec une BDR classique, une fois la structure créée, il est seulement possible d'ajouter des données qui correspondent à la structure de la base.
</br>
Avec une BDOD, le développeur peut facilement insérer n'importe quel type de données dans n'importe quelle collection, ce qui implique un gros risque d'avoir des structures de documents inconsistentes ou obsolètes avec de nombreuses mises à jour. (source [1](http://insights.dice.com/2012/06/04/nosql-document-storage-benefits-drawbacks/), [2](http://www.dbta.com/Columns/Notes-on-NoSQL/NoSQL-and-Document-Oriented-Databases-72035.aspx))

### Quelles sont les types de données stockés dans Redis?
Redis est une base de données open source de type clefs-valeurs mono-threadée. ([source](http://www.barreverte.fr/une-courte-introduction-a-redis/))</br>
Elle accueille les données structurées suivantes : des chaînes de caractères, des listes, des hashs, des sets, des sets triés, des bitmaps, des [hyperloglogs](https://redis.io/topics/data-types-intro#hyperloglogs) ou encore des coordonnées GPS.
([source](https://redis.io/topics/introduction))
### Que peut on faire comme types de requêtes ?
Chaque structure de données gérée possède des caractéristiques uniques et commandes uniques.
Il est impossible de requêter les valeurs comme on le fait habituellement avec un WHERE dans une BDR, une valeur en table **n'est accessible que via une clé**.
Alors qu'une clé est stockée comme un tableau de bytes, on utilise essentiellement une string come clé.</br>
#### Exemples de requêtes selon la structure de données:
##### Strings
```
SET pages:about "about us"
GET pages:about
about us
```
##### Hashes
```
HSET rennes code_postal 35000
HSET rennes region "Bretagne"
HGET rennes code_postal
35000
```
##### Lists
```
# get the 10 newest users
keys = redis.lrange('users:newest', 0, 10)
#multi get the actual 10 user objects
redis.mget(*keys)
```
##### Sets
```
SADD friends:leto ghanima
SADD friends:leto duncan
SADD friends:paul duncan
SADD friends:paul gurney
SINTER friends:leto friends:paul
1) "duncan"
```
##### Sorted sets
```
ZADD friends:leto 1000 ghanima
ZADD friends:leto 994 duncan
ZADD friends:leto 2 farad'n
ZRANGEBYSCORE friends:leto 500 1000
1) "duncan"
2) "ghanima"
```
[source](http://openmymind.net/2011/11/8/Redis-Zero-To-Master-In-30-Minutes-Part-1/), avec de bonnes explications.
