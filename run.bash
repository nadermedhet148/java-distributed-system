cd naming-service
./mvnw package
docker build . -t naming-service
cd ..
cd config-service
./mvnw package
docker build . -t config-service
cd ..
cd user-service
./mvnw package
docker build . -t user-service
cd ..




