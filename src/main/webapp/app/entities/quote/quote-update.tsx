import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { QuoteStatus } from 'app/shared/model/enumerations/quote-status.model';
import { createEntity, getEntity, updateEntity } from './quote.reducer';

export const QuoteUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const quoteEntity = useAppSelector(state => state.newapp.quote.entity);
  const loading = useAppSelector(state => state.newapp.quote.loading);
  const updating = useAppSelector(state => state.newapp.quote.updating);
  const updateSuccess = useAppSelector(state => state.newapp.quote.updateSuccess);
  const quoteStatusValues = Object.keys(QuoteStatus);

  const handleClose = () => {
    navigate('/quote');
  };

  useEffect(() => {
    if (!isNew) {
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
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.lastModifiedDate = convertDateTimeToServer(values.lastModifiedDate);

    const entity = {
      ...quoteEntity,
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
          createdDate: displayDefaultDateTime(),
          lastModifiedDate: displayDefaultDateTime(),
        }
      : {
          status: 'CREATED',
          ...quoteEntity,
          createdDate: convertDateTimeFromServer(quoteEntity.createdDate),
          lastModifiedDate: convertDateTimeFromServer(quoteEntity.lastModifiedDate),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="newApp.quote.home.createOrEditLabel" data-cy="QuoteCreateUpdateHeading">
            <Translate contentKey="newApp.quote.home.createOrEditLabel">Create or edit a Quote</Translate>
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
                  id="quote-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('newApp.quote.identifier')}
                id="quote-identifier"
                name="identifier"
                data-cy="identifier"
                type="text"
              />
              <ValidatedField
                label={translate('newApp.quote.description')}
                id="quote-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('newApp.quote.createdBy')}
                id="quote-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('newApp.quote.createdDate')}
                id="quote-createdDate"
                name="createdDate"
                data-cy="createdDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('newApp.quote.lastModifiedBy')}
                id="quote-lastModifiedBy"
                name="lastModifiedBy"
                data-cy="lastModifiedBy"
                type="text"
              />
              <ValidatedField
                label={translate('newApp.quote.lastModifiedDate')}
                id="quote-lastModifiedDate"
                name="lastModifiedDate"
                data-cy="lastModifiedDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField label={translate('newApp.quote.status')} id="quote-status" name="status" data-cy="status" type="select">
                {quoteStatusValues.map(quoteStatus => (
                  <option value={quoteStatus} key={quoteStatus}>
                    {translate(`newApp.QuoteStatus.${quoteStatus}`)}
                  </option>
                ))}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/quote" replace color="info">
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

export default QuoteUpdate;
