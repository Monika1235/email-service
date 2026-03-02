Step 1 — Start RabbitMQ
-----------------------

If using Docker:
docker run -d --hostname rabbit-host \
  -p 5672:5672 -p 15672:15672 \
  --name rabbitmq rabbitmq:3-management

Then open:
http://localhost:15672

Login:
guest / guest

If RabbitMQ is already installed locally:
sudo systemctl start rabbitmq-server

Step 2 - Run Application
------------------------

Step 3 - Verify Queue Created
-----------------------------

Go to:
http://localhost:15672

Check:
Queues → account.created.queue

If exists → good.

Step 4 - Now run your auth-service and create an account
--------------------------------------------------------

You should see in email-service logs:
Received message: {...}
Email sent successfully

And email arrives.
