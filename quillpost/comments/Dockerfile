FROM python:3.9
ENV PYTHONUNBUFFERED 1
RUN apt-get update && apt-get install -y netcat-openbsd && apt-get clean
WORKDIR /app
COPY requirements.txt /app/requirements.txt
RUN pip install -r requirements.txt
COPY . /app
RUN chmod +x wait_script.sh
CMD ["sh","/app/wait_script.sh"]