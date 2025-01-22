import dayjs from 'dayjs';

export interface IInvoice {
  id?: number;
  invoiceId?: number | null;
  agencyId?: number | null;
  statusId?: number | null;
  title?: string | null;
  subhead?: string | null;
  invoiceNumber?: string | null;
  poNumber?: string | null;
  dueData?: dayjs.Dayjs | null;
  pdfUrl?: string | null;
}

export const defaultValue: Readonly<IInvoice> = {};
