#ifndef SRC_LIB_MESSAGE_IMESSAGE_H_
#define SRC_LIB_MESSAGE_IMESSAGE_H_

#include <string>

class IMessage
{
public:
  /** IMPORTANT! Need to declare a constructor for interface to work properly */
  virtual ~IMessage() {}

  virtual std::string getMessageString() = 0;
};

#endif // SRC_LIB_MESSAGE_IMESSAGE_H_
