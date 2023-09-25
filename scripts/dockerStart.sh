#!/bin/bash

# start and wait postgres database is running
docker compose -f docker-compose.dev.yml up -d
until docker exec todo_api_dev_postgres psql todo postgres > /dev/null 2>&1; do
  echo 'waiting for postgres container...'
  sleep 1
done