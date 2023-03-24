# 노드 버전 지정
FROM node:18.12.1

LABEL email="lsh80165@gmail.com"

ENV APP_HOME=/usr/app

WORKDIR $APP_HOME

COPY ./.next ./.next

#어떤 포트에서 listen할지
EXPOSE 3000

ENTRYPOINT [ "npm","run","start" ]

