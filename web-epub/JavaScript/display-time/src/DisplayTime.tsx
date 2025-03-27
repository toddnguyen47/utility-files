import { useState, useEffect } from "react";
import { formatInTimeZone } from "date-fns-tz";

const TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssxxx"

const formatWithTimeZone = (date: Date, timeZone: string): string => {

  // Format the date with the time zne
  return formatInTimeZone(date, timeZone, TIME_FORMAT);
}

const DisplayTime: React.FC = () => {
  const [currentTime, setCurrentTime] = useState<Date>(new Date());

  useEffect(() => {
    const intervalId = setInterval(() => {
      setCurrentTime(new Date());
    }, 250);

    return () => clearInterval(intervalId);
  }, []);

  return (
    <div className="flex flex-col items-center justify-center min-h-screen p-4 font-sans">
      <p className="text-2xl">
        <strong>Local Time:</strong> {formatWithTimeZone(currentTime, "America/Los_Angeles")}
      </p>
      <p className="text-2xl">
        <strong>UTC Time:</strong> {formatWithTimeZone(currentTime, "Etc/UTC")}
      </p>
    </div>
  );
};

export default DisplayTime;
