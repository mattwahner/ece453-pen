#!/bin/bash
cd frontend/
npm run build
cd ..
cd backend/
./gradlew build
cd ..
