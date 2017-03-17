# JPA example on MySQL
## Yaml configuration file
see application.yml (Ps, bootstrap.yml which is usually configured on cloud usage has higher priority then application.yml)

## @OneToMany and @ManyToOne
### Single Direction Binding
@ManyToOne is configured on the 'many' side, with EAGER fetch type as default, @JoinColumn is necessary which represented as foreign key name.
```JAVA
class Mail {
    ...
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User user;
    ...
}
```

### Both Directions Binding
1. @OneToMany is configured on the 'one' side, with LAZY fetch type as default, mappedBy 'one' entity's name which is defined on other side.
2. @ManyToOne is configured as single-direction-binding described
```JAVA
class Mail {
    ...
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    private User user;
    ...
}
class User {
    ...
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Mail> mails;
    ...
}
```

## @NamedQuery and Join
### Named Query
Name query annotation can be defined on entity class, the name should be also defined in repository interface, which can be called from other methods.
```JAVA
@NamedQuery(name = "User.findAllCustom", query = "select u from User u where u.username is not null")
class User {
...
}
@Repository
interface UserRepository extends JpaRepository<User, String> {
    List<Users> findAllCustom(); //defined as a named query which loads users with names non-null
}
```

### Join
#### Combined with @NamedQuery or @Query (in repository interface)
Query can be 
``` SQL
select a from A a join a.b b where b.id=?1
select a from A a where a.b.id=?1
```

Or can be 
```
select a from A a join FETCH a.b where b.id=?1
```

```JAVA
@NamedQuery(name = "Mail.findByUserId", query = "select m from Mail m join fetch m.user u where u.id=?1")
public class Mail {
...
}
@Repository
public interface MailRepository extends JpaRepository<Mail, String> {
    List<Mail> findByUserId(String userid);
}
```

Or just in repository interfaces
```JAVA
@Repository
public interface MailRepository extends JpaRepository<Mail, String> {
    @Query("select m from Mail m join fetch m.user u where u.id=?1")
    List<Mail> findByUserId(String userid);
}
```

## Note
### Dependencies
following dependencies have to be added to run this app
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

### @Table, @Entity
1. @Entity without @Table, entity name is table name (in lower case in mysql)
2. @Entity with @Table, entity name is defined as table name
3. schema, etc, can be ignored when using mysql, which are used on other rdms.

### @GeneratedValue
Can only generate int value so is unnecessary when id is String type.