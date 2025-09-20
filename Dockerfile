# ARM64 호환 이미지 사용
FROM eclipse-temurin:17-jdk AS build
WORKDIR /build

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean build --no-daemon

# 실행 스테이지
FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /app

# Timezone
ENV TZ=Asia/Seoul

RUN apk --no-cache add tzdata curl sqlite \
    && ln -snf /usr/share/zoneinfo/"$TZ" /etc/localtime \
    && cp /usr/share/zoneinfo/Asia/Seoul /etc/localtime \
    && echo "$TZ" > /etc/timezone \
    && apk del tzdata

COPY --from=build /build/script/data.sql /app/data.sql
COPY --from=build /build/build/libs/*.jar /app/app.jar

RUN sqlite3 /app/db.sqlite < /app/data.sql

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
