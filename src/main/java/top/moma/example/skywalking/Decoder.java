package top.moma.example.skywalking;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Decoder {

  public static void main(String[] args) {
    // bXg6Om5wOjp3YWxsZXQtc2VydmljZQ==
    // 8f95df66735174258b6ab27b1af632e3f.43594.16607437861292443
    String content =
        "CjU1YjIxZDE0NmU1ZTU0Y2ZiODljY2M0YmU2OGNlZDJkOC44MS4xNjYyNDg3ODM0MDIzNDc0ORI2NTRkZTg5M2I4YzI5NDAyNmEwYzY0MjNiMmY1MDk3ZDUuMTU3LjE2NjI0OTM3ODA5NzcyMTgyGjQIARjy1+eisTAg8tfnorEwMh5KYWNrc29uL09iamVjdE1hcHBlci5yZWFkVmFsdWVAAlB2GmAIAxACGPXX56KxMCD11+eisTAyC0xldHR1Y2UvR0VUOhIxMC4xNTIuMTgwLjgyOjYzNzlAAUgFUDliEAoHZGIudHlwZRIFUmVkaXNiEwoMZGIuc3RhdGVtZW50EgNHRVQaYQgFEAQY9dfnorEwIPbX56KxMDILTGV0dHVjZS9HRVQ6EzEwLjE1Mi4xMTkuMTk0OjYzNzlAAUgFUDliEAoHZGIudHlwZRIFUmVkaXNiEwoMZGIuc3RhdGVtZW50EgNHRVQaSggEEAIY9dfnorEwIPbX56KxMDIybXgubmFub3BheS53ZWIuc2VydmljZS5pbXBsLlNtc0ZhY3RvcnkuZ2V0U2Jmckxpc3RAAlBdGk8IBhACGPbX56KxMCD21+eisTAyN214Lm5hbm9wYXkud2ViLnNlcnZpY2UuaW1wbC5TbXNGYWN0b3J5LnNlbGVjdFNtc0NoYW5uZWxAAlBdGkQICBAHGPbX56KxMCD21+eisTAyLG14Lm5hbm9wYXkud2ViLmNvbmZpZy5Ub29sc0NvbmZpZy5nZXRXYXZ5VXJsQAJQXRpICAkQBxj21+eisTAg9tfnorEwMjBteC5uYW5vcGF5LndlYi5jb25maWcuVG9vbHNDb25maWcuZ2V0V2F2eUFjY291bnRAAlBdGkoIChAHGPbX56KxMCD21+eisTAyMm14Lm5hbm9wYXkud2ViLmNvbmZpZy5Ub29sc0NvbmZpZy5nZXRXYXZ5QWNjZXNzS2V5QAJQXRq+QggMEAsY9tfnorEwIOTb56KxMDIML3YxL3NlbmQtc21zOh1hcGktbWVzc2FnaW5nLndhdnkuZ2xvYmFsOjQ0M0ABSANQDVgBYjQKA3VybBItaHR0cHM6Ly9hcGktbWVzc2FnaW5nLndhdnkuZ2xvYmFsL3YxL3NlbmQtc21zYhIKC2h0dHAubWV0aG9kEgNHRVRiFwoQaHR0cC5zdGF0dXNfY29kZRIDNDAzascgCOTb56KxMBIOCgVldmVudBIFZXJyb3ISTwoKZXJyb3Iua2luZBJBb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50Lkh0dHBDbGllbnRFcnJvckV4Y2VwdGlvbiRGb3JiaWRkZW4SGAoHbWVzc2FnZRINNDAzIEZvcmJpZGRlbhLCHwoFc3RhY2sSuB9vcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuSHR0cENsaWVudEVycm9yRXhjZXB0aW9uJEZvcmJpZGRlbjogNDAzIEZvcmJpZGRlbgphdCBvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuSHR0cENsaWVudEVycm9yRXhjZXB0aW9uLmNyZWF0ZShIdHRwQ2xpZW50RXJyb3JFeGNlcHRpb24uamF2YTo4MykKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LkRlZmF1bHRSZXNwb25zZUVycm9ySGFuZGxlci5oYW5kbGVFcnJvcihEZWZhdWx0UmVzcG9uc2VFcnJvckhhbmRsZXIuamF2YToxMjIpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5EZWZhdWx0UmVzcG9uc2VFcnJvckhhbmRsZXIuaGFuZGxlRXJyb3IoRGVmYXVsdFJlc3BvbnNlRXJyb3JIYW5kbGVyLmphdmE6MTAyKQphdCBvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuUmVzcG9uc2VFcnJvckhhbmRsZXIuaGFuZGxlRXJyb3IoUmVzcG9uc2VFcnJvckhhbmRsZXIuamF2YTo2MykKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LlJlc3RUZW1wbGF0ZS5oYW5kbGVSZXNwb25zZSRvcmlnaW5hbCRBM3phclR1MihSZXN0VGVtcGxhdGUuamF2YTo3NzgpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUuaGFuZGxlUmVzcG9uc2Ukb3JpZ2luYWwkQTN6YXJUdTIkYWNjZXNzb3IkcHVmY283TDgoUmVzdFRlbXBsYXRlLmphdmEpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUkYXV4aWxpYXJ5JG9xNDhBdnY5LmNhbGwoVW5rbm93biBTb3VyY2UpCmF0IG9yZy5hcGFjaGUuc2t5d2Fsa2luZy5hcG0uYWdlbnQuY29yZS5wbHVnaW4uaW50ZXJjZXB0b3IuZW5oYW5jZS5JbnN0TWV0aG9kc0ludGVyLmludGVyY2VwdChJbnN0TWV0aG9kc0ludGVyLmphdmE6ODYpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUuaGFuZGxlUmVzcG9uc2UoUmVzdFRlbXBsYXRlLmphdmEpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUuZG9FeGVjdXRlJG9yaWdpbmFsJEEzemFyVHUyKFJlc3RUZW1wbGF0ZS5qYXZhOjczNikKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LlJlc3RUZW1wbGF0ZS5kb0V4ZWN1dGUkb3JpZ2luYWwkQTN6YXJUdTIkYWNjZXNzb3IkcHVmY283TDgoUmVzdFRlbXBsYXRlLmphdmEpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUkYXV4aWxpYXJ5JHZZZ3lkeEk0LmNhbGwoVW5rbm93biBTb3VyY2UpCmF0IG9yZy5hcGFjaGUuc2t5d2Fsa2luZy5hcG0uYWdlbnQuY29yZS5wbHVnaW4uaW50ZXJjZXB0b3IuZW5oYW5jZS5JbnN0TWV0aG9kc0ludGVyLmludGVyY2VwdChJbnN0TWV0aG9kc0ludGVyLmphdmE6ODYpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUuZG9FeGVjdXRlKFJlc3RUZW1wbGF0ZS5qYXZhKQphdCBvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuUmVzdFRlbXBsYXRlLmV4ZWN1dGUoUmVzdFRlbXBsYXRlLmphdmE6NjcwKQphdCBvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuUmVzdFRlbXBsYXRlLmV4Y2hhbmdlKFJlc3RUZW1wbGF0ZS5qYXZhOjU3OSkKYXQgbXgubmFub3BheS53ZWIudXRpbC5Ub29sc0h0dHBVdGlscy5kb1JlcXVlc3RXYXZ5SW50ZXJmYWNlJG9yaWdpbmFsJDhIRDdyWHB2KFRvb2xzSHR0cFV0aWxzLmphdmE6OTgpCmF0IG14Lm5hbm9wYXkud2ViLnV0aWwuVG9vbHNIdHRwVXRpbHMuZG9SZXF1ZXN0V2F2eUludGVyZmFjZSRvcmlnaW5hbCQ4SEQ3clhwdiRhY2Nlc3NvciRUT3NlcTJqZihUb29sc0h0dHBVdGlscy5qYXZhKQphdCBteC5uYW5vcGF5LndlYi51dGlsLlRvb2xzSHR0cFV0aWxzJGF1eGlsaWFyeSRBOG1yanFNSy5jYWxsKFVua25vd24gU291cmNlKQphdCBvcmcuYXBhY2hlLnNreXdhbGtpbmcuYXBtLmFnZW50LmNvcmUucGx1Z2luLmludGVyY2VwdG9yLmVuaGFuY2UuSW5zdE1ldGhvZHNJbnRlci5pbnRlcmNlcHQoSW5zdE1ldGhvZHNJbnRlci5qYXZhOjg2KQphdCBteC5uYW5vcGF5LndlYi51dGlsLlRvb2xzSHR0cFV0aWxzLmRvUmVxdWVzdFdhdnlJbnRlcmZhY2UoVG9vbHNIdHRwVXRpbHMuamF2YSkKYXQgbXgubmFub3BheS53ZWIuc2VydmljZS5pbXBsLldhdnlTbXNTdHJhdGVneS5zZW5kT3RwJG9yaWdpbmFsJFUxSUxiYzF1KFdhdnlTbXNTdHJhdGVneS5qYXZhOjYwKQphdCBteC5uYW5vcGF5LndlYi5zZXJ2aWNlLmltcGwuV2F2eVNtc1N0cmF0ZWd5LnNlbmRPdHAkb3JpZ2luYWwkVTFJTGJjMXUkYWNjZXNzb3IkS2JidkUzaUkoV2F2eVNtc1N0cmF0ZWd5LmphdmEpCmF0IG14Lm5hbm9wYXkud2ViLnNlcnZpY2UuaW1wbC5XYXZ5U21zU3RyYXRlZ3kkYXV4aWxpYXJ5JDZzVjRrMFB3LmNhbGwoVW5rbm93biBTb3VyY2UpCmF0IG9yZy5hcGFjaGUuc2t5d2Fsa2luZy5hcG0uYWdlbnQuY29yZS5wbHVnaW4uaW50ZXJjZXB0b3IuZW5oYW5jZS5JbnN0TWV0aG9kc0ludGVyLmludGVyY2VwdChJbnN0TWV0aG9kc0ludGVyLmphdmE6ODYpCmF0IG14Lm5hbm9wYXkud2ViLnNlcnZpY2UuaW1wbC5XYXZ5U21zU3RyYXRlZ3kuc2VuZE90cChXYXZ5U21zU3RyYXRlZ3kuamF2YSkKYXQgbXgubmFub3BheS53ZWIuc2VydmljZS5pbXBsLlNtc0ZhY3RvcnlTZXJ2aWNlSW1wbC5jaG9vc2VTbXNDaGFubmVsRG9TZW5kJG9yaWdpbmFsJG5jdkJJMHJ4KFNtc0ZhY3RvcnlTZXJ2aWNlSW1wbC5qYXZhOjUyKQphdCBteC5uYW5vcGF5LndlYi5zZXJ2aWNlLmltcGwuU21zRmFjdG9yeVNlcnZpY2VJbXBsLmNob29zZVNtc0NoYW5uZWxEb1NlbmQkb3JpZ2luYWwkbmN2QkkwcngkYWNjZXNzb3IkdDl4VU5lMnkoU21zRmFjdG9yeVNlcnZpY2VJbXBsLmphdmEpCmF0IG14Lm5hbm9wYXkud2ViLnNlcnZpY2UuaW1wbC5TbXNGYWN0b3J5U2VydmljZUltcGwkYXV4aWxpYXJ5JFhuOVptTjNRLmNhbGwoVW5rbm93biBTb3VyY2UpCmF0IG9yZy5hcGFjaGUuc2t5d2Fsa2luZy5hcG0uYWdlbnQuY29yZS5wbHVnaW4uaW50ZXJjZXB0b3IuZW5oYW5jZS5JbnN0TWV0aG9kc0ludGVyLmludGVyY2VwdChJbnN0TWV0aG9kc0ludGVyLmphdmE6ODYpCmF0IG14Lm5hbm9wYXkud2ViLnNlcnZpY2UuaW1wbC5TbXNGYWN0b3J5U2VydmljZUltcGwuY2hvb3NlU21zQ2hhbm5lbERvU2VuZChTbXNGYWN0b3J5U2VydmljZUltcGwuamF2YSkKYXQgbXgubmFub3BheS53ZWIuY29udHJvbGxlci5TbXNDb250cm9sbGVyLnNlbmRPdHAkb3JpZ2luYWwkNVRSeXRkcXcoU21zQ29udHJvbGxlci5qYXZhOjM1KQphdCBteC5uYW5vcGF5LndlYi5jb250cm9sbGVyLlNtc0NvbnRyb2xsZXIuc2VuZE90cCRvcmlnaW5hbCQ1VFJ5dGRxdyRhY2Nlc3NvciRFWjN4VlhGZShTbXNDb250cm9sbGVyLmphdmEpCmF0IG14Lm5hbm9wYXkud2ViLmNvbnRyb2xsZXIuU21zQ29udHJvbGxlciRhdXhpbGlhcnkkakdpbHhZTXcuY2FsbChVbmtub3duIFNvdXJjZSkKYXQgb3JnLmFwYWNoZS5za3l3YWxraW5nLmFwbS5hZ2VudC5jb3JlLnBsdWdpbi5pbnRlcmNlcHRvci5lbmhhbmNlLkluc3RNZXRob2RzSW50ZXIuaW50ZXJjZXB0KEluc3RNZXRob2RzSW50ZXIuamF2YTo4NikKYXQgbXgubmFub3BheS53ZWIuY29udHJvbGxlci5TbXNDb250cm9sbGVyLnNlbmRPdHAoU21zQ29udHJvbGxlci5qYXZhKQphdCBzdW4ucmVmbGVjdC5HZW5lcmF0ZWRNZXRob2RBY2Nlc3NvcjI0My5pbnZva2UoVW5rbm93biBTb3VyY2UpCmF0IHN1bi5yZWZsZWN0LkRlbGVnYXRpbmdNZXRob2RBY2Nlc3NvckltcGwuaW52b2tlKERlbGVnYXRpbmdNZXRob2RBY2Nlc3NvckltcGwuamF2YTo0MykKYXQgamF2YS5sYW5nLnJlZmxlY3QuTWV0aG9kLmludm9rZShNZXRob2QuamF2YTo0OTgpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLm1ldGhvZC5zdXBwb3J0Lkludm9jYWJsZUhhbmRsZXJNZXRob2QuZG9JbnZva2UoSW52b2NhYmxlSGFuZGxlck1ldGhvZC5qYXZhOjE4OSkKascgCOTb56KxMBIOCgVldmVudBIFZXJyb3ISTwoKZXJyb3Iua2luZBJBb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50Lkh0dHBDbGllbnRFcnJvckV4Y2VwdGlvbiRGb3JiaWRkZW4SGAoHbWVzc2FnZRINNDAzIEZvcmJpZGRlbhLCHwoFc3RhY2sSuB9vcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuSHR0cENsaWVudEVycm9yRXhjZXB0aW9uJEZvcmJpZGRlbjogNDAzIEZvcmJpZGRlbgphdCBvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuSHR0cENsaWVudEVycm9yRXhjZXB0aW9uLmNyZWF0ZShIdHRwQ2xpZW50RXJyb3JFeGNlcHRpb24uamF2YTo4MykKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LkRlZmF1bHRSZXNwb25zZUVycm9ySGFuZGxlci5oYW5kbGVFcnJvcihEZWZhdWx0UmVzcG9uc2VFcnJvckhhbmRsZXIuamF2YToxMjIpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5EZWZhdWx0UmVzcG9uc2VFcnJvckhhbmRsZXIuaGFuZGxlRXJyb3IoRGVmYXVsdFJlc3BvbnNlRXJyb3JIYW5kbGVyLmphdmE6MTAyKQphdCBvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuUmVzcG9uc2VFcnJvckhhbmRsZXIuaGFuZGxlRXJyb3IoUmVzcG9uc2VFcnJvckhhbmRsZXIuamF2YTo2MykKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LlJlc3RUZW1wbGF0ZS5oYW5kbGVSZXNwb25zZSRvcmlnaW5hbCRBM3phclR1MihSZXN0VGVtcGxhdGUuamF2YTo3NzgpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUuaGFuZGxlUmVzcG9uc2Ukb3JpZ2luYWwkQTN6YXJUdTIkYWNjZXNzb3IkcHVmY283TDgoUmVzdFRlbXBsYXRlLmphdmEpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUkYXV4aWxpYXJ5JG9xNDhBdnY5LmNhbGwoVW5rbm93biBTb3VyY2UpCmF0IG9yZy5hcGFjaGUuc2t5d2Fsa2luZy5hcG0uYWdlbnQuY29yZS5wbHVnaW4uaW50ZXJjZXB0b3IuZW5oYW5jZS5JbnN0TWV0aG9kc0ludGVyLmludGVyY2VwdChJbnN0TWV0aG9kc0ludGVyLmphdmE6ODYpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUuaGFuZGxlUmVzcG9uc2UoUmVzdFRlbXBsYXRlLmphdmEpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUuZG9FeGVjdXRlJG9yaWdpbmFsJEEzemFyVHUyKFJlc3RUZW1wbGF0ZS5qYXZhOjczNikKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LlJlc3RUZW1wbGF0ZS5kb0V4ZWN1dGUkb3JpZ2luYWwkQTN6YXJUdTIkYWNjZXNzb3IkcHVmY283TDgoUmVzdFRlbXBsYXRlLmphdmEpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUkYXV4aWxpYXJ5JHZZZ3lkeEk0LmNhbGwoVW5rbm93biBTb3VyY2UpCmF0IG9yZy5hcGFjaGUuc2t5d2Fsa2luZy5hcG0uYWdlbnQuY29yZS5wbHVnaW4uaW50ZXJjZXB0b3IuZW5oYW5jZS5JbnN0TWV0aG9kc0ludGVyLmludGVyY2VwdChJbnN0TWV0aG9kc0ludGVyLmphdmE6ODYpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUuZG9FeGVjdXRlKFJlc3RUZW1wbGF0ZS5qYXZhKQphdCBvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuUmVzdFRlbXBsYXRlLmV4ZWN1dGUoUmVzdFRlbXBsYXRlLmphdmE6NjcwKQphdCBvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuUmVzdFRlbXBsYXRlLmV4Y2hhbmdlKFJlc3RUZW1wbGF0ZS5qYXZhOjU3OSkKYXQgbXgubmFub3BheS53ZWIudXRpbC5Ub29sc0h0dHBVdGlscy5kb1JlcXVlc3RXYXZ5SW50ZXJmYWNlJG9yaWdpbmFsJDhIRDdyWHB2KFRvb2xzSHR0cFV0aWxzLmphdmE6OTgpCmF0IG14Lm5hbm9wYXkud2ViLnV0aWwuVG9vbHNIdHRwVXRpbHMuZG9SZXF1ZXN0V2F2eUludGVyZmFjZSRvcmlnaW5hbCQ4SEQ3clhwdiRhY2Nlc3NvciRUT3NlcTJqZihUb29sc0h0dHBVdGlscy5qYXZhKQphdCBteC5uYW5vcGF5LndlYi51dGlsLlRvb2xzSHR0cFV0aWxzJGF1eGlsaWFyeSRBOG1yanFNSy5jYWxsKFVua25vd24gU291cmNlKQphdCBvcmcuYXBhY2hlLnNreXdhbGtpbmcuYXBtLmFnZW50LmNvcmUucGx1Z2luLmludGVyY2VwdG9yLmVuaGFuY2UuSW5zdE1ldGhvZHNJbnRlci5pbnRlcmNlcHQoSW5zdE1ldGhvZHNJbnRlci5qYXZhOjg2KQphdCBteC5uYW5vcGF5LndlYi51dGlsLlRvb2xzSHR0cFV0aWxzLmRvUmVxdWVzdFdhdnlJbnRlcmZhY2UoVG9vbHNIdHRwVXRpbHMuamF2YSkKYXQgbXgubmFub3BheS53ZWIuc2VydmljZS5pbXBsLldhdnlTbXNTdHJhdGVneS5zZW5kT3RwJG9yaWdpbmFsJFUxSUxiYzF1KFdhdnlTbXNTdHJhdGVneS5qYXZhOjYwKQphdCBteC5uYW5vcGF5LndlYi5zZXJ2aWNlLmltcGwuV2F2eVNtc1N0cmF0ZWd5LnNlbmRPdHAkb3JpZ2luYWwkVTFJTGJjMXUkYWNjZXNzb3IkS2JidkUzaUkoV2F2eVNtc1N0cmF0ZWd5LmphdmEpCmF0IG14Lm5hbm9wYXkud2ViLnNlcnZpY2UuaW1wbC5XYXZ5U21zU3RyYXRlZ3kkYXV4aWxpYXJ5JDZzVjRrMFB3LmNhbGwoVW5rbm93biBTb3VyY2UpCmF0IG9yZy5hcGFjaGUuc2t5d2Fsa2luZy5hcG0uYWdlbnQuY29yZS5wbHVnaW4uaW50ZXJjZXB0b3IuZW5oYW5jZS5JbnN0TWV0aG9kc0ludGVyLmludGVyY2VwdChJbnN0TWV0aG9kc0ludGVyLmphdmE6ODYpCmF0IG14Lm5hbm9wYXkud2ViLnNlcnZpY2UuaW1wbC5XYXZ5U21zU3RyYXRlZ3kuc2VuZE90cChXYXZ5U21zU3RyYXRlZ3kuamF2YSkKYXQgbXgubmFub3BheS53ZWIuc2VydmljZS5pbXBsLlNtc0ZhY3RvcnlTZXJ2aWNlSW1wbC5jaG9vc2VTbXNDaGFubmVsRG9TZW5kJG9yaWdpbmFsJG5jdkJJMHJ4KFNtc0ZhY3RvcnlTZXJ2aWNlSW1wbC5qYXZhOjUyKQphdCBteC5uYW5vcGF5LndlYi5zZXJ2aWNlLmltcGwuU21zRmFjdG9yeVNlcnZpY2VJbXBsLmNob29zZVNtc0NoYW5uZWxEb1NlbmQkb3JpZ2luYWwkbmN2QkkwcngkYWNjZXNzb3IkdDl4VU5lMnkoU21zRmFjdG9yeVNlcnZpY2VJbXBsLmphdmEpCmF0IG14Lm5hbm9wYXkud2ViLnNlcnZpY2UuaW1wbC5TbXNGYWN0b3J5U2VydmljZUltcGwkYXV4aWxpYXJ5JFhuOVptTjNRLmNhbGwoVW5rbm93biBTb3VyY2UpCmF0IG9yZy5hcGFjaGUuc2t5d2Fsa2luZy5hcG0uYWdlbnQuY29yZS5wbHVnaW4uaW50ZXJjZXB0b3IuZW5oYW5jZS5JbnN0TWV0aG9kc0ludGVyLmludGVyY2VwdChJbnN0TWV0aG9kc0ludGVyLmphdmE6ODYpCmF0IG14Lm5hbm9wYXkud2ViLnNlcnZpY2UuaW1wbC5TbXNGYWN0b3J5U2VydmljZUltcGwuY2hvb3NlU21zQ2hhbm5lbERvU2VuZChTbXNGYWN0b3J5U2VydmljZUltcGwuamF2YSkKYXQgbXgubmFub3BheS53ZWIuY29udHJvbGxlci5TbXNDb250cm9sbGVyLnNlbmRPdHAkb3JpZ2luYWwkNVRSeXRkcXcoU21zQ29udHJvbGxlci5qYXZhOjM1KQphdCBteC5uYW5vcGF5LndlYi5jb250cm9sbGVyLlNtc0NvbnRyb2xsZXIuc2VuZE90cCRvcmlnaW5hbCQ1VFJ5dGRxdyRhY2Nlc3NvciRFWjN4VlhGZShTbXNDb250cm9sbGVyLmphdmEpCmF0IG14Lm5hbm9wYXkud2ViLmNvbnRyb2xsZXIuU21zQ29udHJvbGxlciRhdXhpbGlhcnkkakdpbHhZTXcuY2FsbChVbmtub3duIFNvdXJjZSkKYXQgb3JnLmFwYWNoZS5za3l3YWxraW5nLmFwbS5hZ2VudC5jb3JlLnBsdWdpbi5pbnRlcmNlcHRvci5lbmhhbmNlLkluc3RNZXRob2RzSW50ZXIuaW50ZXJjZXB0KEluc3RNZXRob2RzSW50ZXIuamF2YTo4NikKYXQgbXgubmFub3BheS53ZWIuY29udHJvbGxlci5TbXNDb250cm9sbGVyLnNlbmRPdHAoU21zQ29udHJvbGxlci5qYXZhKQphdCBzdW4ucmVmbGVjdC5HZW5lcmF0ZWRNZXRob2RBY2Nlc3NvcjI0My5pbnZva2UoVW5rbm93biBTb3VyY2UpCmF0IHN1bi5yZWZsZWN0LkRlbGVnYXRpbmdNZXRob2RBY2Nlc3NvckltcGwuaW52b2tlKERlbGVnYXRpbmdNZXRob2RBY2Nlc3NvckltcGwuamF2YTo0MykKYXQgamF2YS5sYW5nLnJlZmxlY3QuTWV0aG9kLmludm9rZShNZXRob2QuamF2YTo0OTgpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLm1ldGhvZC5zdXBwb3J0Lkludm9jYWJsZUhhbmRsZXJNZXRob2QuZG9JbnZva2UoSW52b2NhYmxlSGFuZGxlck1ldGhvZC5qYXZhOjE4OSkKGp0hCAsQBxj21+eisTAg5NvnorEwMjlteC5uYW5vcGF5LndlYi51dGlsLlRvb2xzSHR0cFV0aWxzLmRvUmVxdWVzdFdhdnlJbnRlcmZhY2VAAlBdWAFqxyAI5NvnorEwEg4KBWV2ZW50EgVlcnJvchJPCgplcnJvci5raW5kEkFvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuSHR0cENsaWVudEVycm9yRXhjZXB0aW9uJEZvcmJpZGRlbhIYCgdtZXNzYWdlEg00MDMgRm9yYmlkZGVuEsIfCgVzdGFjaxK4H29yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5IdHRwQ2xpZW50RXJyb3JFeGNlcHRpb24kRm9yYmlkZGVuOiA0MDMgRm9yYmlkZGVuCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5IdHRwQ2xpZW50RXJyb3JFeGNlcHRpb24uY3JlYXRlKEh0dHBDbGllbnRFcnJvckV4Y2VwdGlvbi5qYXZhOjgzKQphdCBvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuRGVmYXVsdFJlc3BvbnNlRXJyb3JIYW5kbGVyLmhhbmRsZUVycm9yKERlZmF1bHRSZXNwb25zZUVycm9ySGFuZGxlci5qYXZhOjEyMikKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LkRlZmF1bHRSZXNwb25zZUVycm9ySGFuZGxlci5oYW5kbGVFcnJvcihEZWZhdWx0UmVzcG9uc2VFcnJvckhhbmRsZXIuamF2YToxMDIpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXNwb25zZUVycm9ySGFuZGxlci5oYW5kbGVFcnJvcihSZXNwb25zZUVycm9ySGFuZGxlci5qYXZhOjYzKQphdCBvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuUmVzdFRlbXBsYXRlLmhhbmRsZVJlc3BvbnNlJG9yaWdpbmFsJEEzemFyVHUyKFJlc3RUZW1wbGF0ZS5qYXZhOjc3OCkKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LlJlc3RUZW1wbGF0ZS5oYW5kbGVSZXNwb25zZSRvcmlnaW5hbCRBM3phclR1MiRhY2Nlc3NvciRwdWZjbzdMOChSZXN0VGVtcGxhdGUuamF2YSkKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LlJlc3RUZW1wbGF0ZSRhdXhpbGlhcnkkb3E0OEF2djkuY2FsbChVbmtub3duIFNvdXJjZSkKYXQgb3JnLmFwYWNoZS5za3l3YWxraW5nLmFwbS5hZ2VudC5jb3JlLnBsdWdpbi5pbnRlcmNlcHRvci5lbmhhbmNlLkluc3RNZXRob2RzSW50ZXIuaW50ZXJjZXB0KEluc3RNZXRob2RzSW50ZXIuamF2YTo4NikKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LlJlc3RUZW1wbGF0ZS5oYW5kbGVSZXNwb25zZShSZXN0VGVtcGxhdGUuamF2YSkKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LlJlc3RUZW1wbGF0ZS5kb0V4ZWN1dGUkb3JpZ2luYWwkQTN6YXJUdTIoUmVzdFRlbXBsYXRlLmphdmE6NzM2KQphdCBvcmcuc3ByaW5nZnJhbWV3b3JrLndlYi5jbGllbnQuUmVzdFRlbXBsYXRlLmRvRXhlY3V0ZSRvcmlnaW5hbCRBM3phclR1MiRhY2Nlc3NvciRwdWZjbzdMOChSZXN0VGVtcGxhdGUuamF2YSkKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LlJlc3RUZW1wbGF0ZSRhdXhpbGlhcnkkdllneWR4STQuY2FsbChVbmtub3duIFNvdXJjZSkKYXQgb3JnLmFwYWNoZS5za3l3YWxraW5nLmFwbS5hZ2VudC5jb3JlLnBsdWdpbi5pbnRlcmNlcHRvci5lbmhhbmNlLkluc3RNZXRob2RzSW50ZXIuaW50ZXJjZXB0KEluc3RNZXRob2RzSW50ZXIuamF2YTo4NikKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIuY2xpZW50LlJlc3RUZW1wbGF0ZS5kb0V4ZWN1dGUoUmVzdFRlbXBsYXRlLmphdmEpCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUuZXhlY3V0ZShSZXN0VGVtcGxhdGUuamF2YTo2NzApCmF0IG9yZy5zcHJpbmdmcmFtZXdvcmsud2ViLmNsaWVudC5SZXN0VGVtcGxhdGUuZXhjaGFuZ2UoUmVzdFRlbXBsYXRlLmphdmE6NTc5KQphdCBteC5uYW5vcGF5LndlYi51dGlsLlRvb2xzSHR0cFV0aWxzLmRvUmVxdWVzdFdhdnlJbnRlcmZhY2Ukb3JpZ2luYWwkOEhEN3JYcHYoVG9vbHNIdHRwVXRpbHMuamF2YTo5OCkKYXQgbXgubmFub3BheS53ZWIudXRpbC5Ub29sc0h0dHBVdGlscy5kb1JlcXVlc3RXYXZ5SW50ZXJmYWNlJG9yaWdpbmFsJDhIRDdyWHB2JGFjY2Vzc29yJFRPc2VxMmpmKFRvb2xzSHR0cFV0aWxzLmphdmEpCmF0IG14Lm5hbm9wYXkud2ViLnV0aWwuVG9vbHNIdHRwVXRpbHMkYXV4aWxpYXJ5JEE4bXJqcU1LLmNhbGwoVW5rbm93biBTb3VyY2UpCmF0IG9yZy5hcGFjaGUuc2t5d2Fsa2luZy5hcG0uYWdlbnQuY29yZS5wbHVnaW4uaW50ZXJjZXB0b3IuZW5oYW5jZS5JbnN0TWV0aG9kc0ludGVyLmludGVyY2VwdChJbnN0TWV0aG9kc0ludGVyLmphdmE6ODYpCmF0IG14Lm5hbm9wYXkud2ViLnV0aWwuVG9vbHNIdHRwVXRpbHMuZG9SZXF1ZXN0V2F2eUludGVyZmFjZShUb29sc0h0dHBVdGlscy5qYXZhKQphdCBteC5uYW5vcGF5LndlYi5zZXJ2aWNlLmltcGwuV2F2eVNtc1N0cmF0ZWd5LnNlbmRPdHAkb3JpZ2luYWwkVTFJTGJjMXUoV2F2eVNtc1N0cmF0ZWd5LmphdmE6NjApCmF0IG14Lm5hbm9wYXkud2ViLnNlcnZpY2UuaW1wbC5XYXZ5U21zU3RyYXRlZ3kuc2VuZE90cCRvcmlnaW5hbCRVMUlMYmMxdSRhY2Nlc3NvciRLYmJ2RTNpSShXYXZ5U21zU3RyYXRlZ3kuamF2YSkKYXQgbXgubmFub3BheS53ZWIuc2VydmljZS5pbXBsLldhdnlTbXNTdHJhdGVneSRhdXhpbGlhcnkkNnNWNGswUHcuY2FsbChVbmtub3duIFNvdXJjZSkKYXQgb3JnLmFwYWNoZS5za3l3YWxraW5nLmFwbS5hZ2VudC5jb3JlLnBsdWdpbi5pbnRlcmNlcHRvci5lbmhhbmNlLkluc3RNZXRob2RzSW50ZXIuaW50ZXJjZXB0KEluc3RNZXRob2RzSW50ZXIuamF2YTo4NikKYXQgbXgubmFub3BheS53ZWIuc2VydmljZS5pbXBsLldhdnlTbXNTdHJhdGVneS5zZW5kT3RwKFdhdnlTbXNTdHJhdGVneS5qYXZhKQphdCBteC5uYW5vcGF5LndlYi5zZXJ2aWNlLmltcGwuU21zRmFjdG9yeVNlcnZpY2VJbXBsLmNob29zZVNtc0NoYW5uZWxEb1NlbmQkb3JpZ2luYWwkbmN2QkkwcngoU21zRmFjdG9yeVNlcnZpY2VJbXBsLmphdmE6NTIpCmF0IG14Lm5hbm9wYXkud2ViLnNlcnZpY2UuaW1wbC5TbXNGYWN0b3J5U2VydmljZUltcGwuY2hvb3NlU21zQ2hhbm5lbERvU2VuZCRvcmlnaW5hbCRuY3ZCSTByeCRhY2Nlc3NvciR0OXhVTmUyeShTbXNGYWN0b3J5U2VydmljZUltcGwuamF2YSkKYXQgbXgubmFub3BheS53ZWIuc2VydmljZS5pbXBsLlNtc0ZhY3RvcnlTZXJ2aWNlSW1wbCRhdXhpbGlhcnkkWG45Wm1OM1EuY2FsbChVbmtub3duIFNvdXJjZSkKYXQgb3JnLmFwYWNoZS5za3l3YWxraW5nLmFwbS5hZ2VudC5jb3JlLnBsdWdpbi5pbnRlcmNlcHRvci5lbmhhbmNlLkluc3RNZXRob2RzSW50ZXIuaW50ZXJjZXB0KEluc3RNZXRob2RzSW50ZXIuamF2YTo4NikKYXQgbXgubmFub3BheS53ZWIuc2VydmljZS5pbXBsLlNtc0ZhY3RvcnlTZXJ2aWNlSW1wbC5jaG9vc2VTbXNDaGFubmVsRG9TZW5kKFNtc0ZhY3RvcnlTZXJ2aWNlSW1wbC5qYXZhKQphdCBteC5uYW5vcGF5LndlYi5jb250cm9sbGVyLlNtc0NvbnRyb2xsZXIuc2VuZE90cCRvcmlnaW5hbCQ1VFJ5dGRxdyhTbXNDb250cm9sbGVyLmphdmE6MzUpCmF0IG14Lm5hbm9wYXkud2ViLmNvbnRyb2xsZXIuU21zQ29udHJvbGxlci5zZW5kT3RwJG9yaWdpbmFsJDVUUnl0ZHF3JGFjY2Vzc29yJEVaM3hWWEZlKFNtc0NvbnRyb2xsZXIuamF2YSkKYXQgbXgubmFub3BheS53ZWIuY29udHJvbGxlci5TbXNDb250cm9sbGVyJGF1eGlsaWFyeSRqR2lseFlNdy5jYWxsKFVua25vd24gU291cmNlKQphdCBvcmcuYXBhY2hlLnNreXdhbGtpbmcuYXBtLmFnZW50LmNvcmUucGx1Z2luLmludGVyY2VwdG9yLmVuaGFuY2UuSW5zdE1ldGhvZHNJbnRlci5pbnRlcmNlcHQoSW5zdE1ldGhvZHNJbnRlci5qYXZhOjg2KQphdCBteC5uYW5vcGF5LndlYi5jb250cm9sbGVyLlNtc0NvbnRyb2xsZXIuc2VuZE90cChTbXNDb250cm9sbGVyLmphdmEpCmF0IHN1bi5yZWZsZWN0LkdlbmVyYXRlZE1ldGhvZEFjY2Vzc29yMjQzLmludm9rZShVbmtub3duIFNvdXJjZSkKYXQgc3VuLnJlZmxlY3QuRGVsZWdhdGluZ01ldGhvZEFjY2Vzc29ySW1wbC5pbnZva2UoRGVsZWdhdGluZ01ldGhvZEFjY2Vzc29ySW1wbC5qYXZhOjQzKQphdCBqYXZhLmxhbmcucmVmbGVjdC5NZXRob2QuaW52b2tlKE1ldGhvZC5qYXZhOjQ5OCkKYXQgb3JnLnNwcmluZ2ZyYW1ld29yay53ZWIubWV0aG9kLnN1cHBvcnQuSW52b2NhYmxlSGFuZGxlck1ldGhvZC5kb0ludm9rZShJbnZvY2FibGVIYW5kbGVyTWV0aG9kLmphdmE6MTg5KQoaSwgHEAIY9tfnorEwIOXb56KxMDIzbXgubmFub3BheS53ZWIuc2VydmljZS5pbXBsLldhdnlTbXNTdHJhdGVneS5zZW5kT3RwQAJQXRpeCAIY8tfnorEwIOfb56KxMDJIbXgubmFub3BheS53ZWIuc2VydmljZS5pbXBsLlNtc0ZhY3RvcnlTZXJ2aWNlSW1wbC5jaG9vc2VTbXNDaGFubmVsRG9TZW5kQAJQXRo1CA0Y59vnorEwIOfb56KxMDIfSmFja3Nvbi9PYmplY3RXcml0ZXIud3JpdGVWYWx1ZUACUHYa3gIQ////////////ARjx1+eisTAg59vnorEwKuoBEjU1YjIxZDE0NmU1ZTU0Y2ZiODljY2M0YmU2OGNlZDJkOC44MS4xNjYyNDg3ODM0MDIzNDc0ORo2Y2RkYTUxNjhmODhjNDJjMGI0MzZhZTRlMWFkYzcwNDMuNjcxLjE2NjI0ODc4MzMzMzQ3ODIyKg9teDo6bnA6OnVjZW50ZXIyLTQ1NWExMzZhZTEyZTRhMDQ5Zjc0NjllYTk0YjNlYTFkQDE3Mi4xNi4wLjIwMTomUE9TVDovdWNlbnRlci9hcGkvdjIvdmVyeWZ5SDVNb2JpbGVPdHBCETE3Mi4xNi4wLjE4MTo2NTA0MhFQT1NUOi9zbXMvc2VuZE90cEgDUA5iKgoDdXJsEiNodHRwOi8vMTcyLjE2LjAuNTY6NjUwMy9zbXMvc2VuZE90cGITCgtodHRwLm1ldGhvZBIEUE9TVCIRbXg6Om5wOjp0b29scy13ZWIqLGZkNTRiNmU1NjFkNTQ5NzA4NDgxOTM0MDU5MGEwODhjQDE3Mi4xNi4wLjU2";
    //String content = "bXg6Om5wOjp0b29scy13ZWI=";
    String a = new String(Base64.getDecoder().decode(content), StandardCharsets.UTF_8);
    System.out.println(a);
    String text = "co::co::card";
    String b = new String(Base64.getEncoder().encode(text.getBytes(StandardCharsets.UTF_8)));
    System.out.println(b);
  }
}
