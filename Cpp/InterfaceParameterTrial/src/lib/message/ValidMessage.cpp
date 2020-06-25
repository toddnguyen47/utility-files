#include "ValidMessage.hpp"

ValidMessage::ValidMessage(const std::string msg)
{
  msg_ = msg;
}

std::string ValidMessage::getMessageString()
{
  return msg_;
}
