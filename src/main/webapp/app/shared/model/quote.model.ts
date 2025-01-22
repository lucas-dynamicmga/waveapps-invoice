import dayjs from 'dayjs';
import { QuoteStatus } from 'app/shared/model/enumerations/quote-status.model';

export interface IQuote {
  id?: number;
  identifier?: string | null;
  description?: string | null;
  createdBy?: string | null;
  createdDate?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  status?: keyof typeof QuoteStatus | null;
}

export const defaultValue: Readonly<IQuote> = {};
