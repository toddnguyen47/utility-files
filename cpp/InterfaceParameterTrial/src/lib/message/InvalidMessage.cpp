#include "InvalidMessage.hpp"

InvalidMessage::InvalidMessage(const std::string msg)
{
  msg_ = msg;
}

std::string InvalidMessage::getMessageString()
{
  return msg_;
}
