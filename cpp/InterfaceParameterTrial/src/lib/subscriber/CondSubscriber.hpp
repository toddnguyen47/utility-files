#ifndef SRC_LIB_SUBSCRIBER_H_
#define SRC_LIB_SUBSCRIBER_H_

#include "ISubscriber.hpp"

#include <iostream>

class CondSubscriber : public ISubscriber
{
public:
  CondSubscriber(){};

  bool receive(IMessage &message);

private:
};

#endif // SRC_LIB_SUBSCRIBER_H_
