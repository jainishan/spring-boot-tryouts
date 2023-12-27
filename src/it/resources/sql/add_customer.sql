INSERT INTO payment_consent(
id, jaja_customer_id, jaja_account_id, provider_id, scheme_id, active, activation_time, deactivation_time)
VALUES(
101, 200, 300, 'provider_id', 'faster_payments_service', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
