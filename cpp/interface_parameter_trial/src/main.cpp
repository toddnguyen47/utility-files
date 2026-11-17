#include "i_message.hpp"
#include "invalid_message.hpp"
#include "valid_message.hpp"
#include "i_subscriber.hpp"
#include "cond_subscriber.hpp"

#include <iostream>

int main()
{
  IMessage *message = new InvalidMessage("INVALID MESSAGE!");
  ISubscriber *cond_sub = new CondSubscriber();

  bool results = cond_sub->receive(*message);

  message = new ValidMessage("VALID MESSAGE");

  delete (cond_sub);
  delete (message);
}
