package org.elasticmq.impl

import org.apache.log4j.Logger
import org.elasticmq._

class LoggingClient(delegate: Client) extends Client {
  def queueClient = new QueueClientLoggingWrapper(delegate.queueClient)
  def messageClient = new MessageClientLogginWrapper(delegate.messageClient)
}

class QueueClientLoggingWrapper(delegate: QueueClient) extends QueueClient {
  val log = Logger.getLogger(this.getClass)

  def createQueue(queue: Queue) = {
    log.debug("Creating queue: "+queue);
    delegate.createQueue(queue)
  }

  def lookupQueue(name: String) = {
    log.debug("Looking up queue: "+name);
    delegate.lookupQueue(name)
  }

  def updateDefaultVisibilityTimeout(queue: Queue, newDefaultVisibilityTimeout: VisibilityTimeout) = {
    log.debug("Updating default visibility timeout: "+queue+" with: "+newDefaultVisibilityTimeout);
    delegate.updateDefaultVisibilityTimeout(queue, newDefaultVisibilityTimeout)
  }

  def deleteQueue(queue: Queue) {
    log.debug("Deleting queue: " + queue)
    delegate.deleteQueue(queue);
  }

  def listQueues = {
    log.debug("Listing queues")
    delegate.listQueues
  }
}

class MessageClientLogginWrapper(delegate: MessageClient) extends MessageClient {
  val log = Logger.getLogger(this.getClass)

  def sendMessage(message: AnyMessage) = {
    log.debug("Sending message: "+message)
    delegate.sendMessage(message)
  }

  def receiveMessage(queue: Queue) = {
    log.debug("Receiving message: "+queue)
    delegate.receiveMessage(queue)
  }

  def updateVisibilityTimeout(message: AnyMessage, newVisibilityTimeout: VisibilityTimeout) = {
    log.debug("Updating visibility timeout for: "+message+" with: "+newVisibilityTimeout)
    delegate.updateVisibilityTimeout(message, newVisibilityTimeout)
  }

  def deleteMessage(message: AnyMessage) {
    log.debug("Deleting message: "+message)
    delegate.deleteMessage(message)
  }

  def lookupMessage(id: String) = {
    log.debug("Looking up message: "+id)
    delegate.lookupMessage(id)
  }
}