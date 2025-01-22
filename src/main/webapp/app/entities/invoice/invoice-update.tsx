import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './invoice.reducer';

export const InvoiceUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const invoiceEntity = useAppSelector(state => state.newapp.invoice.entity);
  const loading = useAppSelector(state => state.newapp.invoice.loading);
  const updating = useAppSelector(state => state.newapp.invoice.updating);
  const updateSuccess = useAppSelector(state => state.newapp.invoice.updateSuccess);

  const handleClose = () => {
    navigate('/invoice');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.invoiceId !== undefined && typeof values.invoiceId !== 'number') {
      values.invoiceId = Number(values.invoiceId);
    }
    if (values.agencyId !== undefined && typeof values.agencyId !== 'number') {
      values.agencyId = Number(values.agencyId);
    }
    if (values.statusId !== undefined && typeof values.statusId !== 'number') {
      values.statusId = Number(values.statusId);
    }
    values.dueData = convertDateTimeToServer(values.dueData);

    const entity = {
      ...invoiceEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          dueData: displayDefaultDateTime(),
        }
      : {
          ...invoiceEntity,
          dueData: convertDateTimeFromServer(invoiceEntity.dueData),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newApp.invoice.home.createOrEditLabel" data-cy="InvoiceCreateUpdateHeading">
            <Translate contentKey="newApp.invoice.home.createOrEditLabel">Create or edit a Invoice</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="invoice-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('newApp.invoice.invoiceId')}
                id="invoice-invoiceId"
                name="invoiceId"
                data-cy="invoiceId"
                type="text"
              />
              <ValidatedField
                label={translate('newApp.invoice.agencyId')}
                id="invoice-agencyId"
                name="agencyId"
                data-cy="agencyId"
                type="text"
              />
              <ValidatedField
                label={translate('newApp.invoice.statusId')}
                id="invoice-statusId"
                name="statusId"
                data-cy="statusId"
                type="text"
              />
              <ValidatedField label={translate('newApp.invoice.title')} id="invoice-title" name="title" data-cy="title" type="text" />
              <ValidatedField
                label={translate('newApp.invoice.subhead')}
                id="invoice-subhead"
                name="subhead"
                data-cy="subhead"
                type="text"
              />
              <ValidatedField
                label={translate('newApp.invoice.invoiceNumber')}
                id="invoice-invoiceNumber"
                name="invoiceNumber"
                data-cy="invoiceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('newApp.invoice.poNumber')}
                id="invoice-poNumber"
                name="poNumber"
                data-cy="poNumber"
                type="text"
              />
              <ValidatedField
                label={translate('newApp.invoice.dueData')}
                id="invoice-dueData"
                name="dueData"
                data-cy="dueData"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label={translate('newApp.invoice.pdfUrl')} id="invoice-pdfUrl" name="pdfUrl" data-cy="pdfUrl" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/invoice" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default InvoiceUpdate;
