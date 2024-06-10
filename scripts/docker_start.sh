#!/bin/bash

# Docker 이미지 이름과 태그 설정
IMAGE_NAME="career-jump/dev"
TAG="latest"

DOCKERFILE_PATH="."                # Dockerfile 위치
CONTAINER_NAME="career-jump"       # 컨테이너 이름 설정
COMPOSE_FILE="docker-compose.yml"  # Docker Compose 파일 이름

# Docker 이미지 빌드
echo "Building Docker image..."
docker build -t ${IMAGE_NAME}:${TAG} ${DOCKERFILE_PATH}

# 기존 실행 중인 Docker Compose 서비스 중지 및 삭제
if [ "$(docker-compose -f ${COMPOSE_FILE} ps -q)" ]; then
    echo "Stopping and removing existing Docker Compose services..."
    docker-compose -f ${COMPOSE_FILE} down
fi

# Docker Compose 서비스를 빌드 및 실행
echo "Building and starting Docker Compose services..."
docker-compose -f ${COMPOSE_FILE} up --build -d

echo "Docker container ${CONTAINER_NAME} and Docker Compose services are up and running!"
