#ifndef SRC_LIB_SUBSCRIBER_ISUBSCRIBER_H
#define SRC_LIB_SUBSCRIBER_ISUBSCRIBER_H

#include "IMessage.hpp"

class ISubscriber
{
public:
  /** IMPORTANT! Need to declare a constructor for interface to work properly */
  virtual ~ISubscriber() {}

  virtual bool receive(IMessage &message) = 0;
};

#endif // SRC_LIB_SUBSCRIBER_ISUBSCRIBER_H
