#include "IMessage.hpp"
#include "InvalidMessage.hpp"
#include "ValidMessage.hpp"
#include "ISubscriber.hpp"
#include "CondSubscriber.hpp"

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
