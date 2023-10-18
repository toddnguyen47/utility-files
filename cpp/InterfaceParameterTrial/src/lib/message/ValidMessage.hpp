#ifndef SRC_LIB_MESSAGE_VALID_MESSAGE_H_
#define SRC_LIB_MESSAGE_VALID_MESSAGE_H_

#include "IMessage.hpp"

class ValidMessage : public IMessage
{
public:
  ValidMessage(const std::string msg);

  std::string getMessageString();

private:
  std::string msg_;
};

#endif // SRC_LIB_MESSAGE_VALID_MESSAGE_H_
