FROM python:3.9

ENV APP_HOME=/usr/app

WORKDIR $APP_HOME

RUN apt-get -qq update \
    && apt-get -qq install -y --no-install-recommends \
        cron \
    && apt-get -qq autoremove \
    && apt-get -qq clean

COPY ./crawling-env ./crawling-env

RUN cp /home/ubuntu/test/docker/code/cronjob /etc/cron.d/cronjob
RUN chmod +x /etc/cron.d/cronjob
RUN crontab /etc/cron.d/cronjob

CMD ["cron", "-f"]
