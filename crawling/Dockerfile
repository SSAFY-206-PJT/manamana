FROM python:3.9

ENV APP_HOME=/usr/app

WORKDIR $APP_HOME

COPY . .

# COPY ./crawling-env ./crawling-env

RUN apt-get -qq update \
    && apt-get -qq install -y --no-install-recommends \
        cron \
    && apt-get -qq autoremove \
    && apt-get -qq clean

RUN cp ./crawling-env/cronjob /etc/cron.d/cronjob
RUN chmod +x /etc/cron.d/cronjob
RUN crontab /etc/cron.d/cronjob
RUN cp ./crawling/.env /kakao_webtoon/ \
    && cp ./crawling/.env /kakao_page/ \
    && cp ./crawling/config.py /naver_webtoon

CMD ["cron", "-f"]
