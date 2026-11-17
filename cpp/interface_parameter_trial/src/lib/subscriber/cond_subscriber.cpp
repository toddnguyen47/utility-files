#include "cond_subscriber.hpp"

bool CondSubscriber::receive(IMessage &message)
{
  std::cout << message.getMessageString() << std::endl;
  return true;
}
