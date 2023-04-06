FROM python:3.10

ENV APP_HOME=/usr/app/bigdata

WORKDIR $APP_HOME

COPY . .

RUN pip install -r requirements.txt

EXPOSE 8000

CMD ["uvicorn", "main:app", "--reload", "--host", "0.0.0.0", "--port", "8000"]