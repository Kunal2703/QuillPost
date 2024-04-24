FROM node:alpine AS development
ENV NODE_ENV development
WORKDIR /react-app
COPY ./package*.json /react-app
RUN npm ci
COPY . .
CMD ["npm","start"]
