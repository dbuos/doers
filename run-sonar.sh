#!/usr/bin/env bash
./gradlew sonarqube \
  -Dsonar.projectKey=doers-services \
  -Dsonar.organization=dbuos-github \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=7b568aebda4eb8374161010bcc082f7cd08f6283