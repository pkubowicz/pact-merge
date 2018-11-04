Demostrates a problem with pact-jvm way of merging new contract JSON with an existing file.

To reproduce:

```
git reset --hard
rm -rf build/pact
./gradlew test
grep description build/pact/Consumer-Provider.json
sed -i 's/user/admin/g' src/test/java/ContractTest.java
./gradlew test
grep description build/pact/Consumer-Provider.json
```
