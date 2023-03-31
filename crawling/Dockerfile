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
RUN cp ./crawling-env/.env /kakao_webtoon/.env
RUN cp ./crawling-env/.env /kakao_page/.env
RUN cp ./crawling-env/config.py /naver_webtoon/config.py

RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
    && apt install -y ./google-chrome-stable_current_amd64.deb \
    && rm google-chrome-stable_current_amd64.deb

RUN pip install -r requirements.txt

CMD ["cron", "-f"]
