#ifndef SRC_LIB_MESSAGE_INVALID_MESSAGE_H_
#define SRC_LIB_MESSAGE_INVALID_MESSAGE_H_

#include "IMessage.hpp"

class InvalidMessage : public IMessage
{
public:
  InvalidMessage(const std::string msg);

  std::string getMessageString();

private:
  std::string msg_;
};

#endif // SRC_LIB_MESSAGE_INVALID_MESSAGE_H_
