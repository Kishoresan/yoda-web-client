FROM node:10.14.2-alpine

ADD . app/ionic
WORKDIR app/ionic

RUN npm i -g ionic@4.12.0 cordova@9.0.0
RUN npm i -E @angular/cli

RUN ionic cordova build browser --prod --release

CMD cordova run browser --prod --release