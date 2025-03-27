import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './mytailwind.css'
import DisplayTime from './DisplayTime'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <DisplayTime />
  </StrictMode>,
)
