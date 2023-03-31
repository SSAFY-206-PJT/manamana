FROM python:3.9

WORKDIR /home/ubuntu/test/docker/code

RUN apt-get -qq update \
    && apt-get -qq install -y --no-install-recommends \
        cron \
    && apt-get -qq autoremove \
    && apt-get -qq clean

COPY ./code /home/ubuntu/test/docker/code


#RUN mv /home/ubuntu/test/docker/code/id_rsa.gitlab.oth5447 ~/.ssh/
#RUN apt-get install -y curl vim git openssh-client --no-install-recommends

#RUN ssh-keyscan lab.ssafy.com >> /root/.ssh/known_hosts
#RUN git clone -b crawling_develop git@lab.ssafy.com:s08-bigdata-recom-sub2/S08P22B206.git crawling_develop \
#    && . /home/ubuntu/test/crawling_develop/crawling/venv/bin/activate


RUN cp /home/ubuntu/test/docker/code/cronjob /etc/cron.d/cronjob
RUN chmod +x /etc/cron.d/cronjob
RUN crontab /etc/cron.d/cronjob

CMD ["cron", "-f"]
