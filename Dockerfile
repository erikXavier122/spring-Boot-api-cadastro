FROM openjdk

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 8080

CMD["openjdk" , "server.js"]